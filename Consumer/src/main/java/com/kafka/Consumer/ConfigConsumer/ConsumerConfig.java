package com.kafka.Consumer.ConfigConsumer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerConfig {
    @Value("${spring.kafka.bootstrapServers}")
    private String bootstrapServers;
    public Map<String,Object> consumerConfig(){//configuro la serializacion y el envio de mensajes
        Map<String,Object>property=new HashMap<>();
        property.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);//dice cual es el servidor de kafka
        property.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);//serializa nuestros string en bytes
        property.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);//serializa el objeto en bytes
        return property;
    };

    @Bean
    public ConsumerFactory<String,String>consumerFactory(){
  return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }
    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>>consumer(){
 ConcurrentKafkaListenerContainerFactory<String ,String>factory=new ConcurrentKafkaListenerContainerFactory<>();
 factory.setConsumerFactory(consumerFactory());
 return factory;
    }
}
