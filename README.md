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
  <version>1.1-SNAPSHOT</version>
</dependency>
```

#### Example use:

```java
import java.util.List;
import org.bson.conversions.Bson;

public class ExampleApplication {

  public static void main(String[] args) {
    /*
    Create a new instance of the MongodbWrapper and connect to the database
     */
    MongodbWrapper mongodbWrapper = new MongodbWrapper("mongodb://localhost:27017");

    /*
    Create example object
     */
    ExampleObject exampleObject = new ExampleObject("test", UUID.randomUUID());
    
    /*
    Insert one object to database.
     */
    mongodbWrapper.insertOne(exampleObject);
    
    /*
    Delete none object from database.
     */
    mongodbWrapper.deleteOne(exampleObject);
    
    /*
    Delete list of objects from database.
     */
    mongodbWrapper.deleteMany(List.of(exampleObject, exampleObject));
  
    /*
    Delete list of objects from database with custom filters.
     */
    mongodbWrapper.deleteMany(List.of(exampleObject, exampleObject), Filters.eq("key", value));
    
    /*
    Update one object in database.
     */
    mongodbWrapper.updateOne(exampleObject);
   
    /*
    Update object in database with custom filters.
     */
    mongodbWrapper.updateOne(exampleObject, Filters.eq("key", value));
    
    /*
    Update list of objects in database.
     */
    mongodbWrapper.updateMany(List.of(exampleObject, exampleObject));

    /*
    Update list of objects in database with custom filters.
     */
    mongodbWrapper.updateMany(List.of(exampleObject, exampleObject), Filters.eq("key", value));
  }
}

```

#### Example Object
```java
@MongodbEntity(
    database = "shopDatabase",
    collection = "shopCollection"
)
public class ExampleObject {
  
  @SerializedName("_id")
  private final String userName; 
  
  @Exclude
  private final UUID uuid;

  public ExampleObject(String userName, UUID uuid) {
    this.userName = userName;
    this.uuid = uuid;
  }
}
```
