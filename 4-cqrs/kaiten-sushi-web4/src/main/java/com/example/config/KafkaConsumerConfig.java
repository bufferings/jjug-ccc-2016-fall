package com.example.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.example.order.domain.event.StoredEvent;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

  @Autowired
  KafkaProperties kafkaProperties;

  @Bean
  KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, StoredEvent>> kafkaListenerContainerFactory() {
    ConcurrentKafkaListenerContainerFactory<String, StoredEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    factory.setConcurrency(3);
    factory.getContainerProperties().setPollTimeout(3000);
    return factory;
  }

  @Bean
  public ConsumerFactory<String, StoredEvent> consumerFactory() {
    return new DefaultKafkaConsumerFactory<>(consumerConfigs(),
        new StringDeserializer(), new JsonDeserializer<>(StoredEvent.class));
  }

  @Bean
  public Map<String, Object> consumerConfigs() {
    Map<String, Object> propsMap = new HashMap<>();
    propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        kafkaProperties.getBrokerAddress());
    propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
    propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
    propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class);
    propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class);
    propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, "group1");
    propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    return propsMap;
  }

}