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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class SimpleObject {

    public abstract Optional<String> id();

    public abstract String name();

    public abstract Optional<String> title();

    public abstract Optional<String> description();

    static Builder builder() {
        return Builder.builder();
    }

    public abstract Builder toBuilder();

    @AutoValue.Builder
    abstract static class Builder {

        @JsonCreator
        public static Builder builder() {
            return new AutoValue_SimpleObject.Builder();
        }

        @JsonProperty("name")
        abstract Builder name(String name);

        @JsonProperty("id")
        abstract Builder id(String id);

        @JsonProperty("title")
        abstract Builder title(String title);

        @JsonProperty("description")
        abstract Builder description(String description);

        abstract SimpleObject build();
    }

    public static interface DAO extends DormEntityManager<SimpleObject> {

    }
}
