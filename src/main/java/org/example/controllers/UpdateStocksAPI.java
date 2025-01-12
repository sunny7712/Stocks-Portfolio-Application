package org.example.controllers;

import org.example.service.UpdateStocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/update")
public class UpdateStocksAPI {

    @Autowired
    private UpdateStocks updateStocks;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file.csv") MultipartFile file){
        try{
            updateStocks.updateStocks(file);
            return ResponseEntity.ok("Stocks updated successfully.");
        }
        catch (IOException ex){
            return ResponseEntity.status(500).body("An error occurred while processing the CSV file.");
        }
    }
}
