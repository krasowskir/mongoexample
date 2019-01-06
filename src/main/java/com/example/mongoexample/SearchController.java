package com.example.mongoexample;

import com.example.mongoexample.model.Restaurant;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
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
    @GetMapping(value = "/search/{name}", produces = "application/json")
    public List<Restaurant> getSpieler(@PathVariable("name") String name){
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MongoCollection<Document> collection = start.getCollection();
        MongoCursor<Document> cursor = collection.find(regex("name", "^" + name, "i")).iterator();
        List<Restaurant> results = new ArrayList<>();
        try {
            while (cursor.hasNext()){
                Document doc = cursor.next();
                results.add(objectMapper.readValue(doc.toJson(), Restaurant.class));
            }
            return results;
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return null;
    }


}
