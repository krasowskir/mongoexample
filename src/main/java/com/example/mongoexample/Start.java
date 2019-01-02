package com.example.mongoexample;

import com.mongodb.DB;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class Start {

    private static String HOST = "localhost";
    private static int PORT = 27017;
    private static String USERNAME = "richard";
    private static String PASSWORD = "test123";
    private static String DATABASE = "richardsrestaurants";

    private static Logger LOGGER = LoggerFactory.getLogger(Start.class);

    @PostConstruct
    public void connectToMongo() {
        try {

            MongoCredential credentials = MongoCredential.createCredential(USERNAME, DATABASE, PASSWORD.toCharArray());
            MongoClient client = MongoClients.create(MongoClientSettings.builder()
                    .applyToClusterSettings(builder -> builder.hosts(Arrays.asList(new ServerAddress(HOST, PORT))))
                    .credential(credentials)
                    .build());
            MongoDatabase db = client.getDatabase(DATABASE);

            LOGGER.info("erfolgreich mit {HOST} auf port: {PORT} verbunden");
        } catch (Exception e) {
            LOGGER.error("Fehler beim verbinden mit ${HOST} auf port: ${PORT}", e);
        }

    }
}
