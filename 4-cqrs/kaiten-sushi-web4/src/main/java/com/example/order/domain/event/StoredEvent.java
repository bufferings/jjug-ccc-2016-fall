package com.example.order.domain.event;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class StoredEvent {

  private String typeName;

  private String eventBody;

  public StoredEvent() {
  }

  public StoredEvent(DomainEvent domainEvent) {
    this.typeName = domainEvent.getClass().getName();
    this.eventBody = new Gson().toJson(domainEvent);
  }

  @SuppressWarnings("unchecked")
  public <T extends DomainEvent> T toDomainEvent() {
    Class<T> domainEventClass = null;
    try {
      domainEventClass = (Class<T>) Class.forName(this.typeName);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    T domainEvent = new Gson().fromJson(eventBody, domainEventClass);
    return domainEvent;
  }

}
