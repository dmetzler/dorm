/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dorm;

/**
 */
public interface DormStorage {

    /**
     * @param type
     * @param id
     * @return
     * @throws DormStorageNotFoundException
     */
    <T extends DormEntity> T get(Class<T> type, String id) throws DormStorageNotFoundException;

    /**
     * @param type
     * @param object
     * @return
     */
    <T extends DormEntity> T create(Class<T> type, T object);

    /**
     * @param type
     * @param object
     * @return
     */
    <T extends DormEntity> T update(Class<T> type, T object) throws DormStorageNotFoundException;

    /**
     * @param type
     * @param object
     * @return
     */
    <T extends DormEntity> T delete(Class<T> type, T object) throws DormStorageNotFoundException;

}
