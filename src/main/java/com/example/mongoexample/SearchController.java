package com.example.mongoexample;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Filters.regex;

@RestController
public class SearchController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Start start;

    @CrossOrigin(value = "http://localhost:8081")
    @ResponseBody
    @GetMapping(value = "/search/{vorname}", produces = "application/json")
    public List<Player> getSpieler(@PathVariable("vorname") String vorname){
        MongoDatabase database = start.getDatabase();
        MongoCollection<Document> collection = database.getCollection("spielers");
        MongoCursor<Document> cursor = collection.find(regex("vorname", "^" + vorname, "i")).iterator();
        try {
            while (cursor.hasNext()){
                Document doc = cursor.next();
                Player tmpReslt = objectMapper.readValue(doc.toJson(), Player.class);
                return Arrays.asList(tmpReslt);
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return null;
    }


}
