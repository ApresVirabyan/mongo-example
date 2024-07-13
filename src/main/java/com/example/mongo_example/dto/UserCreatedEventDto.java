package com.example.mongo_example.dto;

import lombok.Data;

@Data
public class UserCreatedEventDto {

    private String name;
    private String emailId;
}
