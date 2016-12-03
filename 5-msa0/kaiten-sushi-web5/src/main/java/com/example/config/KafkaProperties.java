package com.example.config;

import static com.example.config.KafkaProperties.KAFKA_PREFIX;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = KAFKA_PREFIX)
public class KafkaProperties {
  public static final String KAFKA_PREFIX = "kafka";

  private String brokerAddress = "localhost:9092";

  private String zookeeperAddress;
}
