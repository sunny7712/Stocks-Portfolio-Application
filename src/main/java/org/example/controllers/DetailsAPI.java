package org.example.controllers;

import jakarta.websocket.server.PathParam;
import org.example.entities.Stock;
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

    @GetMapping(value = "/{stockId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stock> getStockDetails(@PathVariable("stockId") Long stockId){
        try{
            Stock stock = stockService.getStockById(stockId);
            if (stock == null){
                return ResponseEntity.notFound().build();
            }
            else{
                return ResponseEntity.ok(stock);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(404).build();
        }
    }
}