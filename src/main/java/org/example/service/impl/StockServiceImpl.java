package org.example.service.impl;

import org.example.dto.StockDTO;
import org.example.entities.Stocks;
import org.example.repository.StocksRepository;
import org.example.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StocksRepository stocksRepository;

    @Override
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

    public StockDTO getStockByName(String stockName) {
        Stocks stock = stocksRepository.findByStockName(stockName);
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
