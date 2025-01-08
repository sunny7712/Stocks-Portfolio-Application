package org.example.service;

import org.example.entities.Stock;
import org.example.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    @Autowired
    private StockRepository stockRepository;

    public Stock getStockById(Long stockId) {
        return stockRepository.findByStockId(stockId);
    }

    public Stock getStockByName(String stockName){
        return stockRepository.findByStockName(stockName);
    }
}
