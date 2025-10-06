package com.example.SystemPay.config;

import com.example.SystemPay.entity.Account;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.beans.BeanProperty;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class KafkaConfig {

    @Value("spring.kafka.producer.bootstrap-server")
    private String bootstrapServer;

    @Value("spring.kafka.producer.key-serializer")
    private String keySerializer;

    @Value("spring.kafka.producer.value-serializer")
    private String valueSerializer;

    @Value("spring.kafka.producer.backs")
    private String backs;

    @Autowired
    Environment environment;

    Map<String, Object> producerConfig() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, environment.getProperty(bootstrapServer));
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, environment.getProperty(keySerializer));
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, environment.getProperty(valueSerializer));
        config.put(ProducerConfig.ACKS_CONFIG, environment.getProperty(backs));

        return config;
    }


    @Bean
    ProducerFactory<String, Account> producerFactory(){
        return new DefaultKafkaProducerFactory<String, Account>(producerConfig());
    }

    @Bean
    KafkaTemplate<String, Account> kafkaTemplate(){
        return new KafkaTemplate<String, Account>(producerFactory());
    }

    @Bean
    NewTopic createTopic(){
        return TopicBuilder.name("created-topic").
                partitions(3).
                replicas(3).
                configs(Map.of("min.insync.replicas", "2")).
                build();
    }




//    @Value("${spring.kafka.bootstrap-servers:localhost:9092}")
//    private String bootstrapServers;
//
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return props;
//    }
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }
//
//
//    @Value("${kafka.consumer.group.id:system-pay-group}")
//    private String groupId;
//
//    @Bean
//    public Properties kafkaConsumerProperties() {
//        Properties props = new Properties();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
//        return props;
//    }
//
//    @Bean
//    public KafkaConsumer<String, String> kafkaConsumer(Properties kafkaConsumerProperties) {
//        return new KafkaConsumer<>(kafkaConsumerProperties);
//    }


}
