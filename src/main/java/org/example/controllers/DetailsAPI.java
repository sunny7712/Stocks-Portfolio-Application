package org.example.controllers;

import org.example.dto.StockDTO;
import org.example.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stock-details")
public class DetailsAPI {


    @Autowired
    private StockService stockService;

    @GetMapping(value = "/id/{stockId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> getStockDetailsById(@PathVariable("stockId") String stockId){
        if(stockId == null || stockId.trim().isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }
        try{
            StockDTO stock = stockService.getStockById(stockId);
            if (stock == null){
                return ResponseEntity.notFound().build();
            }
            else{
                return ResponseEntity.ok(stock);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping(value = "/name/{stockName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StockDTO> getStockDetailsByName(@PathVariable("stockName") String stockName){
        if(stockName == null || stockName.trim().isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }
        try{
            StockDTO stock = stockService.getStockByName(stockName);
            if (stock == null){
                return ResponseEntity.notFound().build();
            }
            else{
                return ResponseEntity.ok(stock);
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }
}
