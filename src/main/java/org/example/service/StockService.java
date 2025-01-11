package org.example.service;

import org.example.dto.StockDTO;
import org.example.entities.Stocks;
import org.example.repository.StocksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {
    @Autowired
    private StocksRepository stocksRepository;

    public StockDTO getStockById(String stockId) {
        Stocks stock = stocksRepository.findByStockId(stockId);
        return StockDTO.builder()
                .stockId(stock.getStockId())
                .stockName(stock.getStockName())
                .openPrice(stock.getOpenPrice())
                .closePrice(stock.getClosePrice())
                .lowPrice(stock.getLowPrice())
                .highPrice(stock.getHighPrice())
                .settlementPrice(stock.getSettlementPrice()).build();

    }
}
