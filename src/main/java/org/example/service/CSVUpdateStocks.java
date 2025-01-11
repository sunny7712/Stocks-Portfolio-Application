package org.example.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.example.entities.Stocks;
import org.example.repository.StocksRepository;
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
    private StocksRepository stocksRepository;

    @Override
    public void updateStocks(MultipartFile file) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim());

        List<Stocks> stockList = new ArrayList<>();
        csvParser.forEach(record -> {
            String stockId = record.get("SYMBOL");
            Stocks existingStock = stocksRepository.findByStockId(stockId);
            if (existingStock != null) {
                existingStock.setStockName(record.get("SECURITY"));
                existingStock.setOpenPrice(Double.parseDouble(record.get("OPEN_PRICE")));
                existingStock.setClosePrice(Double.parseDouble(record.get("CLOSE_PRICE")));
                existingStock.setLowPrice(Double.parseDouble(record.get("LOW_PRICE")));
                existingStock.setHighPrice(Double.parseDouble(record.get("HIGH_PRICE")));
                existingStock.setSettlementPrice(Double.parseDouble(record.get("CLOSE_PRICE")));
                stockList.add(existingStock);
            } else {
                Stocks stock = new Stocks();
                stock.setStockId(stockId);
                stock.setStockName(record.get("SECURITY"));
                stock.setOpenPrice(Double.parseDouble(record.get("OPEN_PRICE")));
                stock.setClosePrice(Double.parseDouble(record.get("CLOSE_PRICE")));
                stock.setLowPrice(Double.parseDouble(record.get("LOW_PRICE")));
                stock.setHighPrice(Double.parseDouble(record.get("HIGH_PRICE")));
                stock.setSettlementPrice(Double.parseDouble(record.get("CLOSE_PRICE")));
                stockList.add(stock);
            }
        });
        stocksRepository.saveAll(stockList);
    }
}
