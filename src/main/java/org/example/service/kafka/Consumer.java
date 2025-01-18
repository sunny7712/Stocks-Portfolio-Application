package org.example.service.kafka;

import org.example.dto.TradeDTO;

public interface Consumer<T> {
    public void consume(T t);
}
