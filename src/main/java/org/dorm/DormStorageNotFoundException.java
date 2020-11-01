/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dorm;

public class DormStorageNotFoundException extends Exception {

    private final Class<?> type;

    private final String id;

    public DormStorageNotFoundException(Class<?> type, String id) {
        super(String.format("The entity of type %s with ID [%s] was not found", type.getName(), id));
        this.type = type;
        this.id = id;
    }

    public Class<?> getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}
