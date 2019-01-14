package com.example.mongoexample;

import com.example.mongoexample.model.PageResult;
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
import java.util.List;
import java.util.function.Consumer;

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
    public PageResult getSpieler(@PathVariable("name") String name,
                                       @RequestParam("limit") Integer limit,
                                       @RequestParam("page") Integer page){
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MongoCollection<Document> collection = start.getCollection();

        int ammount = 0;
        MongoCursor<Document> cursor = collection.find(regex("name", "^" + name, "i")).iterator();
        while (cursor.hasNext()){
            cursor.next();
            ammount++;
        }
        MongoCursor<Document> cursor2 = collection.find(regex("name", "^" + name, "i")).limit(limit).skip(limit * page).iterator();
        List<Restaurant> results = new ArrayList<>();
        try {

            while (cursor2.hasNext()){
                Document doc = cursor2.next();
                results.add(objectMapper.readValue(doc.toJson(), Restaurant.class));
            }
            return new PageResult(ammount, results);
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return null;
    }



}
