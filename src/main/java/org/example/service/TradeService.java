package org.example.service;

import org.example.dto.TradeDTO;

public interface TradeService {
    public void buyStock(TradeDTO tradeDTO);
    public void sellStock(TradeDTO tradeDTO);
}
