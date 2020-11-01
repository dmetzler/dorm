/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dorm;

public interface DormEntityManager<T> {
    T get(String id) throws DormStorageNotFoundException;

    T create(T object);

    T update(T object) throws DormStorageNotFoundException;

    T delete(T object) throws DormStorageNotFoundException;

}
