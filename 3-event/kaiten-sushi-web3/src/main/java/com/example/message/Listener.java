package com.example.message;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.example.order.domain.event.StoredEvent;

@Component
public class Listener {

  @KafkaListener(id = "foo", topics = "topic1", group = "group1")
  public void listen(StoredEvent storedEvent) {
    System.err.println(storedEvent);
  }

}