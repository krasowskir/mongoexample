package com.example.mongoexample;

import com.mongodb.Cursor;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.mongodb.client.model.Filters.regex;

@RestController
public class SearchController {

    @Autowired
    private Start start;

    @ResponseBody
    @GetMapping("/search/{vorname}")
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
