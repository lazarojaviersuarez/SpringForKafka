package com.kafka.producer.Config;


import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProviderConfig {
    @Value("${spring.kafka.bootstrapServers}")
    private String bootstrapServers;
 public Map<String,Object>providerConfig(){//configuro la serializacion y el envio de mensajes
     Map<String,Object>property=new HashMap<>();
     property.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);//dice cual es el servidor de kafka
     property.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);//serializa nuestros string en bytes
     property.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);//serializa el objeto en bytes
     return property;
 };

 @Bean
 public ProducerFactory<String,String> producerFactory(){
    return new DefaultKafkaProducerFactory<>(providerConfig());
 }

 @Bean
    public KafkaTemplate<String,String>Kafkatemplate(ProducerFactory<String,String> producerFactory){
     return new KafkaTemplate<>(producerFactory);
 }
}
