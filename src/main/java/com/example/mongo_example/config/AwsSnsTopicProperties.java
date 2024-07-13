package com.example.mongo_example.config;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;
import software.amazon.awssdk.annotations.NotNull;

@Getter
@Setter
@Validated
@Configuration
public class AwsSnsTopicProperties {

    @NotBlank(message = "SNS topic ARN must be configured")
    private String topicArn;
}
