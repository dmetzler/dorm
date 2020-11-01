/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dorm;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
class SimpleObjectModule {

    @SuppressWarnings("rawtypes")
    @Provides
    @IntoMap
    @ClassKey(SimpleObject.DAO.class)
    static DormEntityManager simpleObjectDao(DormStorage storage) {
        return new Dorm_SimpleObjectDao(storage);
    };

    @SuppressWarnings("rawtypes")
    @Provides
    @IntoMap
    @StringKey("SimpleObject")
    static Class<? extends DormEntity> typeFor() {
        return Dorm_SimpleObjectEntity.class;

    };

}
