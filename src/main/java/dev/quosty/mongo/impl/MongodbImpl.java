package dev.quosty.mongo.impl;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.util.List;
import org.bson.Document;
import org.bson.conversions.Bson;

public interface MongodbImpl {

  <T> void insertOne(T value);

  <T> void insertMany(List<T> value);

  <T> void deleteOne(T value);

  <T> void deleteMany(T value, Bson filters);

  <T> void updateOne(T value);

  <T> void updateOne(T value, Bson filters);

  <T> void updateMany(List<T> value);

  <T> void updateMany(List<T> value, Bson filters);

  <T> FindIterable<Document> find(T value);

  <T> FindIterable<Document> find(T value, Bson filters);

  MongoCollection<Document> getDatabaseCollection(Class<?> value);


}