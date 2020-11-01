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

final class DormEntityManagerFactory {

    private EntityTypeRegistry registry;

    @Inject
    DormEntityManagerFactory(EntityTypeRegistry registry) {
        this.registry = registry;

    }

    public <T> T getEntityManager(Class<T> daoClass) {
        return (T) registry.getDao(daoClass);
    }

}
