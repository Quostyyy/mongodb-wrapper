package dev.quosty.mongo.impl;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

public interface MongodbImpl {

  <T> void insertOne(T t);

  <T> void deleteOne(T t);

  <T> void updateOne(T t);

  MongoCollection<Document> getDatabaseCollection(Class<?> t);

}