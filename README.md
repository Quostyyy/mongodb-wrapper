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
  <version>1.0.0</version>
</dependency>
```

#### Example use:

```java
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
    Save to database
     */
    mongodbWrapper.insertOne(exampleObject);
    
    /*
    Update in database
     */
    mongodbWrapper.updateOne(exampleObject);
    
    /*
    Delete from database
     */
    mongodbWrapper.deleteOne(exampleObject);
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
