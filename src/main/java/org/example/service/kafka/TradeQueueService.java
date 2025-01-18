package org.example.service.kafka;

import org.example.dto.TradeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TradeQueueService implements QueueService<TradeDTO> {
    private static final String TOPIC = "trade_topic";

    @Autowired
    private KafkaTemplate<String, TradeDTO> kafkaTemplate;

    @Override
    public void send(TradeDTO tradeDTO) {
        kafkaTemplate.send(TOPIC, tradeDTO);
    }
}
