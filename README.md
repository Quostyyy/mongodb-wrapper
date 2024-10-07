# Example Java mongodb-wrapper

---

#### Repository(Maven)
```xml
<repository>
  <id>quosty-repository-releases</id>
  <name>quosty-dev</name>
  <url>https://repo.quosty.dev/releases</url>
</repository>
```
#### Dependency(Maven)
```xml
<dependency>
  <groupId>dev.quosty</groupId>
  <artifactId>mongodb-wrapper</artifactId>
  <version>2.0.0</version>
</dependency>
```

#### Example use:

```java
import com.mongodb.client.model.Filters;

import java.util.List;

public class ExampleApplication {

    public static void main(String[] args) {
        //Create a new instance of the MongoClientInitializer and connect to the database
        MongoClientInitializer initializer = new MongoClientInitializer("mongodb://localhost:27017");
        //new MongoClientInitializer("uri", gson);
        //new MongoClientInitializer(mongoClient);
        //new MongoClientInitializer(mongoClient, gson)

        MongodbWrapperImpl wrapper = initializer.getMongodbWrapper();

        //Create example object
        ExampleObject exampleObject = new ExampleObject("test");

        //Insert one object to database.
        wrapper.insertOne(exampleObject);

        //Insert list of objects to database.
        wrapper.insertMany(List.of(exampleObject, exampleObject));

        //Delete none object from database.
        wrapper.deleteOne(exampleObject);

        //Delete list of objects from database.
        wrapper.deleteMany(exampleObject, Filters.eq("key"));

        //Delete list of objects from database with custom filters.
        wrapper.deleteMany(List.of(exampleObject, exampleObject), Filters.eq("key", value));

        //Update one object in database.
        wrapper.updateOne(exampleObject);

        //Update object in database with custom filters.
        wrapper.updateOne(exampleObject, Filters.eq("key", value));

        //Update list of objects in database.
        wrapper.updateMany(List.of(exampleObject, exampleObject));

        //Update list of objects in database with custom filters.
        wrapper.updateMany(List.of(exampleObject, exampleObject), Filters.eq("key", value));

        //Find object in database with custom filter
        ExampleObject object = wrapper.findOne(ExampleObject.class, Filters.eq("key", value));

        //Find list of objects with custom filter
        List<ExampleObject> objects = wrapper.findMany(ExampleObject.class, Filters.eq("key", value));

        //Checks if the element exists 
        boolean status = wrapper.isExists(ExampleObject.class, Filters.eq("key", value));
    }
}
```

#### Example Object
```java
    @MongodbEntity(
        database = "database",
        collection = "collection"
)
public class ExampleObject {

    @SerializedName("_id")
    private final String userName;

    public ExampleObject(String userName) {
        this.userName = userName;
    }
}
```
