package com.example.mongoexample;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static com.mongodb.client.model.Filters.regex;

@Component
public class Start {

    private static String HOST = "localhost";
    private static int PORT = 27017;
    private static String USERNAME = "richard";
    private static String PASSWORD = "test123";
    private static String DATABASE = "richardsrestaurants";
    private static String COLLECTION = "restaurants";

    private MongoDatabase database;
    private MongoCollection<Document> collection;

    private static Logger LOGGER = LoggerFactory.getLogger(Start.class);

    @PostConstruct
    public void connectToMongo() {
        try {

            MongoCredential credentials = MongoCredential.createCredential(USERNAME, "admin", PASSWORD.toCharArray());
            MongoClient client = MongoClients.create(MongoClientSettings.builder()
                    .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress(HOST, PORT))))
                    .credential(credentials)
                    .build());
            database = client.getDatabase(DATABASE);

            collection = database.getCollection(COLLECTION);
            /*MongoCursor<Document> cursor = collection.find(regex("vorname","^Ric*", "i")).iterator();
            while (cursor.hasNext()){
                Document doc = cursor.next();
                System.out.println("======");
                System.out.println("doc: " + doc.toJson());
            }
            cursor.close();*/

            LOGGER.info("erfolgreich mit {HOST} auf port: {PORT} verbunden");
        } catch (Exception e) {
            LOGGER.error("Fehler beim verbinden mit ${HOST} auf port: ${PORT}", e);
        }
    }

    public MongoDatabase getDatabase(){
        return this.database;
    }

    public MongoCollection getCollection(){
        return this.collection;
    }
}
