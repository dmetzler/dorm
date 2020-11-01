/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dorm;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class InMemoryStorageModule {

    @Binds
    abstract DormStorage getDormStorage(InMemoryStorage storage);
}
