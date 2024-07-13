package com.example.mongo_example.service;


import com.example.mongo_example.config.AwsSnsTopicProperties;
import com.example.mongo_example.dto.UserCreatedEventDto;
import com.example.mongo_example.dto.UserCreationRequestDto;
import io.awspring.cloud.sns.core.SnsTemplate;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.annotations.NotNull;
import software.amazon.awssdk.auth.signer.params.Aws4PresignerParams;

@Slf4j
@RequiredArgsConstructor
@Service
@EnableConfigurationProperties(Aws4PresignerParams.class)
public class UserService {

    private final SnsTemplate snsTemplate;
    private final AwsSnsTopicProperties awsSnsTopicProperties;

    public void create(@NotNull final UserCreationRequestDto userCreationRequest){
        //
        // save user record in database

        final var topicArn = awsSnsTopicProperties.getTopicArn();
        final var payload = convert(userCreationRequest);
        snsTemplate.convertAndSend(topicArn, payload);
        log.info("Successfully published message to topic ARN: {}", topicArn);
    }

    private UserCreatedEventDto convert(@NonNull final UserCreationRequestDto userCreationRequest) {
        final var userCreatedEvent = new UserCreatedEventDto();
        userCreatedEvent.setName(userCreationRequest.getName());
        userCreatedEvent.setEmailId(userCreationRequest.getEmailId());
        return userCreatedEvent;
    }

}
