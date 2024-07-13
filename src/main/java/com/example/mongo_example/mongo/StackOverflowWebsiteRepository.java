package com.example.mongo_example.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StackOverflowWebsiteRepository extends MongoRepository<StackOverflowWebsite, String> {

    List<StackOverflowWebsite> findByWebsite(String website);
}
