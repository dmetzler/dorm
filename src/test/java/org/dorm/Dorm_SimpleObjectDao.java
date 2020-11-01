/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dorm;

import javax.inject.Inject;

public class Dorm_SimpleObjectDao implements SimpleObject.DAO {

    private DormStorage storage;

    @Inject
    public Dorm_SimpleObjectDao(DormStorage storage) {
        this.storage = storage;
    }

    @Override
    public SimpleObject get(String id) throws DormStorageNotFoundException {
        return storage.get(Dorm_SimpleObjectEntity.class, id);
    }

    @Override
    public SimpleObject create(SimpleObject object) {
        return storage.create(Dorm_SimpleObjectEntity.class, new Dorm_SimpleObjectEntity(object));
    }

    @Override
    public SimpleObject update(SimpleObject object) throws DormStorageNotFoundException {
        return storage.update(Dorm_SimpleObjectEntity.class, new Dorm_SimpleObjectEntity(object));
    }

    @Override
    public SimpleObject delete(SimpleObject object) throws DormStorageNotFoundException {
        return storage.delete(Dorm_SimpleObjectEntity.class, new Dorm_SimpleObjectEntity(object));
    }

}
