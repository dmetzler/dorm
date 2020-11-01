/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dorm;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import javax.inject.Singleton;

import org.junit.Before;
import org.junit.Test;

import dagger.Component;

public class SimpleObjectPersistenceTest {

    DormEntityManagerFactory emf;

    private MyComponent dorm;

    private SimpleObject.DAO dao;

    @Singleton
    @Component(modules = { DormModule.class, SimpleObjectModule.class, InMemoryStorageModule.class })
    interface MyComponent extends Dorm {
        DormStorage storage();
    }

    @Before
    public void doBefore() {
        dorm = DaggerSimpleObjectPersistenceTest_MyComponent.create();
        emf = dorm.entityManagerFactory();
        dao = emf.getEntityManager(SimpleObject.DAO.class);
    }

    @Test
    public void storage_is_resolve() throws Exception {
        assertThat(dorm.storage()).isNotNull();
    }

    @Test
    public void can_persist_a_simple_object() throws Exception {

        SimpleObject obj = SimpleObject.builder().name("name").title("title").build();

        assertThat(dao).isNotNull();

        obj = dao.create(obj);

        assertThat(obj).isNotNull();
        assertThat(obj.id()).isNotEmpty();

        Optional<String> id = obj.id();

        obj = dao.get(id.get());
        assertThat(obj).isNotNull();
        assertThat(obj.name()).isEqualTo("name");
        assertThat(obj.title()).isPresent();
        assertThat(obj.title().get()).isEqualTo("title");

    }

    @Test
    public void can_update_a_simple_object() throws Exception {

        SimpleObject obj = SimpleObject.builder().name("name").title("title").build();
        obj = dao.create(obj);

        obj = obj.toBuilder().name("othername").build();

        dao.update(obj);

        obj = dao.get(obj.id().get());

        assertThat(obj.name()).isEqualTo("othername");

    }

    @Test
    public void can_delete_a_simple_object() throws Exception {
        SimpleObject obj = SimpleObject.builder().name("name").title("title").build();
        obj = dao.create(obj);
        String id = obj.id().get();

        dao.delete(obj);

        assertThatThrownBy(() -> {
            dao.get(id);
        }).isInstanceOf(DormStorageNotFoundException.class);

    }
}
