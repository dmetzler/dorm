/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dorm;

public class DormStorageException extends RuntimeException {

    public DormStorageException(String message, Throwable t) {
        super(message, t);
    }

    public DormStorageException(String message) {
        super(message);
    }

}
