package com.kafka.producer.Config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicsConfig {


    @Bean
    public NewTopic generateTopic(){    //asi creo un topico dentro de kafka
        Map<String,String>apa=new HashMap<>();
        apa.put(TopicConfig.CLEANUP_POLICY_CONFIG,TopicConfig.CLEANUP_POLICY_DELETE /* delete(borra) o Compact(mantiene el mas actual)*/);//configuramos el tratamiento de borrado de los mensajes
        apa.put(TopicConfig.RETENTION_MS_CONFIG,"864000000"/*un dia*/);//dice el tiempo que permaneceran los mensajes
        apa.put(TopicConfig.SEGMENT_BYTES_CONFIG,"1073741824"/*1G*/);//tamaño max del segmento se pone en bytes
        apa.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG,"100013");//tamaño max de cada mensaje por defecto viene en 1Mb


        return TopicBuilder.name("Topico17")
                .partitions(3)//dice cuantas particiones tendra este topico
                .replicas(3)//dice que de el mensaje q voy a mandar cree 3 copias en algun otro closter
                .configs(apa)
                .build();
    }
}
