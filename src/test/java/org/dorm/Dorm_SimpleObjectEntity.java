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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Dorm_SimpleObjectEntity.Builder.class)
public class Dorm_SimpleObjectEntity extends SimpleObject implements DormEntity {

    private SimpleObject delegate;

    @Override
    public String type() {
        return "SimpleObject";
    }

    private Dorm_SimpleObjectEntity() {

    }

    @Override
    public Optional<String> title() {
        return delegate.title();
    }

    @Override
    public Optional<String> description() {
        return delegate.description();
    }


    public Dorm_SimpleObjectEntity(SimpleObject delegate) {
        this.delegate = delegate;

    }

    @Override
    public Optional<String> id() {
        return delegate.id();
    }

    @Override
    public String name() {
        return delegate.name();
    }

    @Override
    public DormEntity cloneWithId(String id) {
        return new Dorm_SimpleObjectEntity(delegate.toBuilder().id(id).build());
    }

    @Override
    public org.dorm.SimpleObject.Builder toBuilder() {
        return delegate.toBuilder();
    }

    static class Builder {

        private org.dorm.SimpleObject.Builder delegate;

        private Builder() {
            delegate = SimpleObject.builder();
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder name(String name) {
            delegate.name(name);
            return this;

        }

        public Builder id(String id) {
            delegate.id(id);
            return this;
        }

        public Builder title(String title) {
            delegate.title(title);
            return this;
        }

        public Builder description(String description) {
            delegate.description(description);
            return this;
        }

        public Dorm_SimpleObjectEntity build() {
            return new Dorm_SimpleObjectEntity(delegate.build());
        }

    }

}
