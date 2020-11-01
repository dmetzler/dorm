/*
 * (C) Copyright 2020 Nuxeo (http://nuxeo.com/).
 * This is unpublished proprietary source code of Nuxeo SA. All rights reserved.
 * Notice of copyright on this source code does not indicate publication.
 *
 * Contributors:
 *     dmetzler
 */
package org.dorm;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

/**
 * @since TODO
 */
public class InMemoryStorage implements DormStorage {

    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MAPPER.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        MAPPER.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);
        MAPPER.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        MAPPER.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        MAPPER.registerModule(new Jdk8Module());

        MAPPER.setDateFormat(new StdDateFormat());
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        MAPPER.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
    }

    /**
     * Persist the value of a {@link CloudModelObject} in a JSON representation. Doing this allow to verify that the
     * object can be persisted as JSON.
     */
    private class CloudModelObjectPersistence {

        String jsonValue;

        private String type;

        public CloudModelObjectPersistence(DormEntity value) {
            this.type = value.type();
            try {
                this.jsonValue = MAPPER.writeValueAsString(value);
            } catch (JsonProcessingException e) {
            }
        }

        public DormEntity getValue() {
            try {
                return MAPPER.readValue(this.jsonValue, typeToClass.get(type));
            } catch (IOException e) {
                throw new DormStorageException("Unable to read value from storage", e);
            }
        }

    }

    // JSON based object store
    Map<String, CloudModelObjectPersistence> objects = new HashMap<>();

    private Map<String, Class<? extends DormEntity>> typeToClass;

    @Inject
    public InMemoryStorage(Map<String, Class<? extends DormEntity>> typeToClass) {
        this.typeToClass = typeToClass;

    }

    @Override
    public <T extends DormEntity> T get(Class<T> type, String id) throws DormStorageNotFoundException {
        CloudModelObjectPersistence persistedObject = objects.get(id);
        if (persistedObject == null) {
            throw new DormStorageNotFoundException(type, id);
        }
        if (!type.equals(persistedObject.getValue().getClass())) {
            throw new DormStorageException(
                    "Type mismatch: the retrieved object doesn't have the same type than the one wanted");
        }

        return (T) persistedObject.getValue();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends DormEntity> T create(Class<T> type, T object) {

        if (object.id().isPresent()) {
            throw new DormStorageException("Cannot create an object that already has an id");
        }

        String id = UUID.randomUUID().toString();

        DormEntity entity = object.cloneWithId(id);
        objects.put(id, new CloudModelObjectPersistence(entity));
        return (T) entity;
    }

    @Override
    public <T extends DormEntity> T update(Class<T> type, T object) throws DormStorageNotFoundException {
        if (!object.id().isPresent()) {
            throw new DormStorageException("Cannot update an object without an id");
        } else {

            String id = object.id().get();
            if (!objects.containsKey(id)) {
                throw new DormStorageNotFoundException(type, id);
            }

            objects.put(id, new CloudModelObjectPersistence(object));
            return object;
        }

    }

    @Override
    public <T extends DormEntity> T delete(Class<T> type, T object) throws DormStorageNotFoundException {
        String id = checkExists(type, object);
        objects.remove(id);
        return object;
    }

    private <T extends DormEntity> String checkExists(Class<T> type, T object) throws DormStorageNotFoundException {
        if (!object.id().isPresent()) {
            throw new DormStorageException("Cannot update an object without an id");
        } else {
            return get(type, object.id().get()).id().get();

        }
    }

}
