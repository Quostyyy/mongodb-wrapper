package dev.quosty.mongo.impl;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

public interface MongodbWrapper {

    <T> void insertOne(T value);

    <T> void insertMany(List<T> value);

    <T> void deleteOne(T value);

    <T> void deleteMany(T value, Bson filters);

    <T> void updateOne(T value);

    <T> void updateOne(T value, Bson filters);

    <T> void updateMany(List<T> value);

    <T> void updateMany(List<T> value, Bson filters);

    <T> T findOne(Class<T> clazz, Bson filters);

    <T> List<T> findMany(Class<T> clazz, Bson filters);

    <T> boolean isExists(Class<T> clazz, Bson filters);

    MongoCollection<Document> getDatabaseCollection(Class<?> value);


}