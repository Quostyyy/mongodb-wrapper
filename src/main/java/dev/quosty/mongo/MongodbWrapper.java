package dev.quosty.mongo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import dev.quosty.mongo.adapter.AnnotationExclusionStrategy;
import dev.quosty.mongo.annotations.MongodbEntity;
import dev.quosty.mongo.impl.MongodbImpl;
import org.bson.Document;

public class MongodbWrapper implements MongodbImpl {

  private final MongoClient mongoClient;

  public MongodbWrapper(String mongodbUri) {
    this.mongoClient = MongoClients.create(mongodbUri);
  }

  @Override
  public <T> void insertOne(T t) {
    this.getDatabaseCollection(t.getClass()).insertOne(Document.parse(this.gson.toJson(t)));
  }

  @Override
  public <T> void deleteOne(T t) {
    this.getDatabaseCollection(t.getClass()).findOneAndDelete(Filters.eq("_id", Document.parse(new GsonBuilder().create().toJson(t))));
  }

  @Override
  public <T> void updateOne(T t) {
    this.getDatabaseCollection(t.getClass()).findOneAndUpdate(Filters.eq("_id"), Document.parse(this.gson.toJson(t)));
  }

  @Override
  public MongoCollection<Document> getDatabaseCollection(Class<?> t) {
    return !t.isAnnotationPresent(MongodbEntity.class) ? null : mongoClient
        .getDatabase(t.getAnnotation(MongodbEntity.class).database())
        .getCollection(t.getAnnotation(MongodbEntity.class).collection());
  }

  private final Gson gson = new GsonBuilder()
      .setExclusionStrategies(new AnnotationExclusionStrategy())
      .serializeNulls()
      .create();

  public MongoClient getMongodbClient() {
    return mongoClient;
  }
}
