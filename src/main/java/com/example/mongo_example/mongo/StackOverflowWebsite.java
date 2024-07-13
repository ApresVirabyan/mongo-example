package com.example.mongo_example.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document
public class StackOverflowWebsite {

    @Id
    private final String id;

    @Field
    private final String website;
    @Field
    private final String iconImageUrl;
    @Field
    private final String title;
    @Field
    private final String description;

}
