package org.example.service;

import org.example.dto.StockDTO;

public interface StockService {
    public StockDTO getStockById(String stockId);

    public StockDTO getStockByName(String stockName);
}
