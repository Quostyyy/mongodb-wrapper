package dev.quosty.mongo;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.quosty.mongo.helper.GsonHelper;

public class MongoClientInitializer {

    private final Gson gson;
    private final String database;
    private final MongoClient mongoClient;
    private final MongodbWrapperImpl mongodbWrapper;

    public MongoClientInitializer(MongoClient mongoClient, Gson gson, String database) {
        this.mongoClient = mongoClient;
        this.gson = gson;
        this.database = database;
        this.mongodbWrapper = new MongodbWrapperImpl(this.gson, this.mongoClient, this.database);
    }

    public MongoClientInitializer(MongoClient mongoClient) {
        this(mongoClient, GsonHelper.getGson(), null);
    }

    public MongoClientInitializer(String mongodbUri) {
        this(MongoClients.create(mongodbUri), GsonHelper.getGson(), null);
    }

    public MongoClientInitializer(String mongodbUri, Gson gson) {
        this(MongoClients.create(mongodbUri), gson, null);
    }

    public Gson getGson() {
        return gson;
    }

    public String getDatabase() {
        return database;
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongodbWrapperImpl getMongodbWrapper() {
        return mongodbWrapper;
    }
}
