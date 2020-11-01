/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dorm;

import java.util.Map;

import javax.inject.Inject;

final class EntityTypeRegistry {

    private Map<Class<?>, DormEntityManager> classToEntityManager;

    @SuppressWarnings("rawtypes")
    @Inject
    public EntityTypeRegistry(Map<Class<?>, DormEntityManager> classToEntityManager) {
        this.classToEntityManager = classToEntityManager;
    }

    @SuppressWarnings("unchecked")
    public <T> DormEntityManager<T> getDao(Class<T> entityType) {
        return classToEntityManager.get(entityType);
    }
}
