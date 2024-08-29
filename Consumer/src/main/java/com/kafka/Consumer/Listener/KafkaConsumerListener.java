package com.kafka.Consumer.Listener;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;


@Configuration
public class KafkaConsumerListener {
    private Logger loge= LoggerFactory.getLogger(KafkaConsumerListener.class);
   @KafkaListener(topics = {"Topico17"},groupId = "my-group-id")
    public void Listener(String message){
        loge.info("El mensaje fue recivido :" + message);
    }
}
