/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dorm.dynamodb;

import org.dorm.DormStorage;
import org.dorm.DynamoDBStorage;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class DynamoDBStorageModule {
    @Binds
    abstract DormStorage getDormStorage(DynamoDBStorage storage);
}
