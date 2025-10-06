//package com.example.SystemPay.service;
//
//import jakarta.persistence.Tuple;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.clients.consumer.ConsumerRecords;
//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.util.Collections;
//
//
//@Service
//public class KafkaService {
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    @Autowired
//    private KafkaConsumer<String, String> consumer;
//
//    private static final Logger logger = LoggerFactory.getLogger(KafkaService.class);
//
//    public void sendMessage() {
//        try {
//            logger.info("Attempting to send message to topic: test");
//            kafkaTemplate.send("test-topic", "Precision Products", "France");
//
//            logger.info("Send method completed");
//        } catch (Exception e) {
//            logger.error("Error in sendMessage: {}", e.getMessage());
//        }
//    }
//
//    public void readMessages() {
//        consumer.subscribe(Collections.singletonList("test"));
//        // Бесконечный цикл чтения
//        while (true) {
//            if (consumer == null) {
//                logger.error("KafkaConsumer is not initialized!");
////                throw new IllegalStateException("KafkaConsumer is null");
//            }
//
//
//            // Читаем сообщения с таймаутом 100 мс
//            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
//
//            // Обрабатываем каждое сообщение
//            for (ConsumerRecord<String, String> record : records) {
//                logger.info("Topic: {}, Partition: {}, Offset: {}, Key: {}, Value: {}",
//                        record.topic(),
//                        record.partition(),
//                        record.offset(),
//                        record.key(),
//                        record.value());
//            }
//        }
//    }
//
//
//
//}
