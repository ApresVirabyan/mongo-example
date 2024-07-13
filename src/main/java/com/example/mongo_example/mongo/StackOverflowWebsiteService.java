package com.example.mongo_example.mongo;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class StackOverflowWebsiteService {

    @Autowired
    private StackOverflowWebsiteRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private static List<StackOverflowWebsite> items = new ArrayList<>();
    private static List<Student> students= new ArrayList<>();

    static {

        items.add(new StackOverflowWebsite("stack", "adadasdas", "addasdasd", "fdsafdfds", "dasdasd"));
    }

    public List<StackOverflowWebsite> findAll(){
        return repository.findAll();
    }

    @PostConstruct
    public void init(){
        repository.saveAll(items);
    }

    public List<StackOverflowWebsite> findByWebsite(String website){
        Query query = new Query();
        query.addCriteria(Criteria.where("website").is(website));
        return mongoTemplate.find(query, StackOverflowWebsite.class);
    }
}
