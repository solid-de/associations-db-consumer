package edu.cnam.nfe101.associations.db.consumer.service;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.cnam.nfe101.associations.db.consumer.dto.AssociationJson;

@Service
public class AssociationConsumer {
    
    private static final Logger log = LoggerFactory.getLogger(AssociationConsumer.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final String associationsTopicName;
    private final KafkaConsumer<String, String> consumer;
    private final AssociationService associationService;


     public AssociationConsumer(@Value("${associations.kafka-server}") String kafkaServers, 
                                @Value("${associations.raw-kafka-topic}") String associationsTopicName,
                                AssociationService associationService) {
        this.associationsTopicName = associationsTopicName;
        this.associationService = associationService;

        Map<String, Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServers);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "associations-db-consumer");
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OffsetResetStrategy.EARLIEST.toString());
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 100);
        
        this.consumer = new KafkaConsumer<>(properties);
    }

    public void consume() {
        consumer.subscribe(List.of(associationsTopicName));

         while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

            for (ConsumerRecord<String, String> record : records){
                log.debug("Received record with key: {} from partition: {} at offset: {}", record.key(), record.partition(), record.offset());
                try {
                    AssociationJson jsonAssociation = objectMapper.readValue(record.value(), AssociationJson.class);
                    associationService.save(jsonAssociation);
                } catch (Exception e) {
                    log.error("Error while processing record with key: {} at partition/offset : {}/{}. Stopping consumption", record.key(), record.partition(), record.offset(), e);
                    throw new RuntimeException(e);
                } 
            }
            log.info("Processed {} records", records.count());
        }
    }

}
