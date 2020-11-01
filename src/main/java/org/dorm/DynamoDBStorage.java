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

public class DynamoDBStorage implements DormStorage {

    /**
     *
     */
    @Inject
    public DynamoDBStorage() {
    }

    @Override
    public <T extends DormEntity> T get(Class<T> type, String id) {
        // TODO Auto-generated method stub
        // return null;
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DormEntity> T create(Class<T> type, T object) {
        // TODO Auto-generated method stub
        // return null;
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DormEntity> T update(Class<T> type, T object) {
        // TODO Auto-generated method stub
        // return null;
        throw new UnsupportedOperationException();
    }

    @Override
    public <T extends DormEntity> T delete(Class<T> type, T object) {
        // TODO Auto-generated method stub
        // return null;
        throw new UnsupportedOperationException();
    }


}
