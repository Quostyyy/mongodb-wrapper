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
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongodbWrapper implements MongodbImpl {

  private final MongoClient mongoClient;

  private final Gson gson = new GsonBuilder()
      .setExclusionStrategies(new AnnotationExclusionStrategy())
      .serializeNulls()
      .create();

  public MongodbWrapper(String mongodbUri) {
    this.mongoClient = MongoClients.create(mongodbUri);
  }
  @Override
  public <T> void insertOne(T value) {
    this.getDatabaseCollection(value.getClass()).insertOne(Document.parse(this.gson.toJson(value)));
  }

  @Override
  public <T> void insertMany(List<T> value) {
    value.forEach(this::insertOne);
  }

  @Override
  public <T> void deleteOne(T value) {
    this.getDatabaseCollection(value.getClass()).findOneAndDelete(Filters.eq("_id", Document.parse(this.gson.toJson(value))));
  }

  @Override
  public <T> void deleteMany(T value, Bson filters) {
    this.getDatabaseCollection(value.getClass()).deleteMany(filters);
  }

  @Override
  public <T> void updateOne(T value) {
    this.getDatabaseCollection(value.getClass()).findOneAndUpdate(Filters.eq("_id"), Document.parse(this.gson.toJson(value)));
  }

  @Override
  public <T> void updateOne(T value, Bson filters) {
    this.getDatabaseCollection(value.getClass()).findOneAndUpdate(filters, Document.parse(this.gson.toJson(value)));
  }

  @Override
  public <T> void updateMany(List<T> value) {
    value.forEach(this::updateOne);
  }

  @Override
  public <T> void updateMany(List<T> value, Bson filters) {
    value.forEach(object -> this.updateOne(object, filters));
  }

  @Override
  public MongoCollection<Document> getDatabaseCollection(Class<?> value) {
    return !value.isAnnotationPresent(MongodbEntity.class) ? null : mongoClient
        .getDatabase(value.getAnnotation(MongodbEntity.class).database())
        .getCollection(value.getAnnotation(MongodbEntity.class).collection());
  }

  public MongoClient getMongodbClient() {
    return mongoClient;
  }
}
