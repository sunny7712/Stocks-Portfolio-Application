package org.example.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.example.entities.Stock;
import org.example.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVUpdateStocks implements UpdateStocks {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public void updateStocks(MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim());

        List<Stock> stockList = new ArrayList<>();
        csvParser.forEach(record -> {
            String stockName = record.get("SECURITY");
            Stock existingStock = stockRepository.findByStockName(stockName);
            if (existingStock != null) {
                existingStock.setStockName(record.get("SECURITY"));
                existingStock.setOpenPrice(Double.parseDouble(record.get("OPEN_PRICE")));
                existingStock.setClosePrice(Double.parseDouble(record.get("CLOSE_PRICE")));
                existingStock.setLowPrice(Double.parseDouble(record.get("LOW_PRICE")));
                existingStock.setHighPrice(Double.parseDouble(record.get("HIGH_PRICE")));
                existingStock.setSettlementPrice(Double.parseDouble(record.get("CLOSE_PRICE")));
                stockList.add(existingStock);
            } else {
                Stock stock = new Stock();
                stock.setStockName(record.get("SECURITY"));
                stock.setOpenPrice(Double.parseDouble(record.get("OPEN_PRICE")));
                stock.setClosePrice(Double.parseDouble(record.get("CLOSE_PRICE")));
                stock.setLowPrice(Double.parseDouble(record.get("LOW_PRICE")));
                stock.setHighPrice(Double.parseDouble(record.get("HIGH_PRICE")));
                stock.setSettlementPrice(Double.parseDouble(record.get("CLOSE_PRICE")));
                stockList.add(stock);
            }
        });
        stockRepository.saveAll(stockList);
    }
}
