package com.example.mongo_example.sqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SQSClient {

    @Autowired
    private AmazonSQS sqsClient;

    private String queueUrl;

    public void sendMessage(String msg){
        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(msg)
                .withDelaySeconds(5);
        sqsClient.sendMessage(request);

    }
}
