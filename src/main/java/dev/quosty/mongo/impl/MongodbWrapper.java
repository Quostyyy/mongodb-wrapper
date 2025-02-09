package dev.quosty.mongo.impl;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

public interface MongodbWrapper {

    <T> void insertOne(T value);

    <T> void insertMany(List<T> value);

    <T> void insertOne(String collection, T value);

    <T> void insertMany(String collection, List<T> value);

    <T> void deleteOne(T value);

    <T> void deleteMany(T value, Bson filters);

    <T> void deleteOne(String collection, T value);

    <T> void deleteMany(String collection, T value, Bson filters);

    <T> void updateOne(T value);

    <T> void updateOne(T value, Bson filters);

    <T> void updateOne(String collection, T value);

    <T> void updateOne(String collection, T value, Bson filters);

    <T> void updateMany(List<T> value);

    <T> void updateMany(List<T> value, Bson filters);

    <T> void updateMany(String collection, List<T> value);

    <T> void updateMany(String collection, List<T> value, Bson filters);

    <T> T findOne(Class<T> clazz, Bson filters);

    <T> List<T> findMany(Class<T> clazz, Bson filters);

    <T> boolean isExists(Class<T> clazz, Bson filters);

    <T> T findOne(String collection, Class<T> clazz, Bson filters);

    <T> List<T> findMany(String collection, Class<T> clazz, Bson filters);

    <T> boolean isExists(String collection, Class<T> clazz, Bson filters);

    MongoCollection<Document> getDatabaseCollection(Class<?> value);

    MongoDatabase getDatabase(Class<?> value);

    String getCollectionName(Class<?> value);

}