package com.example.mongoexample;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static com.mongodb.client.model.Filters.regex;

@RestController
public class SearchController {

    @Autowired
    private Start start;

    @ResponseBody
    @GetMapping(value = "/search/{vorname}", produces = "application/json")
    public String getSpieler(@PathVariable("vorname") String vorname){
        MongoDatabase database = start.getDatabase();
        MongoCollection<Document> collection = database.getCollection("spielers");
        MongoCursor<Document> cursor = collection.find(regex("vorname", "^" + vorname, "i")).iterator();
        try {
            while (cursor.hasNext()){
                Document doc = cursor.next();
                return doc.toJson();
            }
        } finally {
            cursor.close();
        }
        return null;
    }
}
