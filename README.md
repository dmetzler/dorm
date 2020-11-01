DORM
====

# What is this?

For nowm, DORM stands for DynamoDB Object Relational Manager. It is a library that helps you write simple immutable beans that can be persisted to a document database like DynamoDB. 

The idea is to help persisting relations between objects so that they are easy to retrieve.

# Design decisions

As the first backend is supposed to be DynamoDB and as it is supposed to be used in AWS Lambda, some design decision have been made to ensure a quick startup:

 * Reflection should not be used (prevents JVM to make any optimization, see https://blog.ippon.tech/best-practices-for-aws-lambda-and-java/ )
 * Dagger 2 is used for the DI framework
 * Beans will have to use Google AutoValue 
 * The library will generate code at compilation (again to avoid reflection)
 * Try to mimic what's existing in JPA (EntityManager API, Entity annotations etc...)

# It all starts with a test

To give a rough overview of its usage lets show an example of how it can work:

First the bean that is using `@AutoValue`. :

```java
@AutoValue
@Entity
public abstract class SimpleObject {

    @Id
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
```

Then the library offers a `DormEntityManager` that know how to persist and retrieve this bean:


```java
    @Before
    public void doBefore() {
        dorm = DaggerSimpleObjectPersistenceTest_MyComponent.create();
        emf = dorm.entityManagerFactory();
        dao = emf.getEntityManager(SimpleObject.DAO.class);
    }
 
    @Test
    public void can_persist_a_simple_object() throws Exception {
    

        SimpleObject obj = SimpleObject.builder()
              .name("name")
              .title("title")
              .build();

        assertThat(dao).isNotNull();

        obj = dao.create(obj);

        assertThat(obj).isNotNull();
        assertThat(obj.id()).isNotEmpty();

        Optional<String> id = obj.id();

        obj = dao.get(id.get());
        assertThat(obj).isNotNull();
        assertThat(obj.name()).isEqualTo("name");
        assertThat(obj.title()).isPresent();
        assertThat(obj.title().get()).isEqualTo("title");

    }
```

# Work in progress

 * ~~POC: Simple CRUD of a bean with no relations~~
 * Code generation for DAO and Entity
 * Alignment with JPA
 * Relations
 * Parent / Child relation
 * Lookup by property
 * Pagination of results
 * Sorting
 * custom `findBy` methods on DAO


# Licensing

The source code is copyright Damien Metzler and
contributors, and licensed under the Apache License, Version 2.0.

See the [LICENSE](LICENSE) file for details.