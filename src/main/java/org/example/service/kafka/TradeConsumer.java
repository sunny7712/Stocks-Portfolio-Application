package org.example.service.kafka;

import org.example.dto.TradeDTO;
import org.example.service.TradeService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TradeConsumer implements Consumer<TradeDTO>{
    private final TradeService tradeService;

    public TradeConsumer(TradeService tradeService){
        this.tradeService = tradeService;
    }

    @KafkaListener(topics = "trade_topic", groupId = "trade_group", containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void consume(TradeDTO tradeDTO){
        if(tradeDTO.getType() == TradeDTO.Type.BUY){
            tradeService.buyStock(tradeDTO);
        }
        else if(tradeDTO.getType() == TradeDTO.Type.SELL){
            tradeService.sellStock(tradeDTO);
        }
    }
}
