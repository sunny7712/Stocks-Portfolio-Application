package org.example.service.kafka;

public interface QueueService<T> {
    public void send(T t);
}
