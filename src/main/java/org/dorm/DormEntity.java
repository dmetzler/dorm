/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dorm;

import java.util.Optional;

public interface DormEntity {

    public Optional<String> id();

    public String type();

    public DormEntity cloneWithId(String id);

}
