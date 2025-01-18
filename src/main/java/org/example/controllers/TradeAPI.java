package org.example.controllers;

import org.example.dto.TradeDTO;
import org.example.service.TradeService;
import org.example.service.kafka.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trade")
public class TradeAPI {

    @Autowired
    TradeService tradeService;

    @Autowired
    QueueService<TradeDTO> queueService;

    @PostMapping(path = "/sync", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> trade(@RequestBody TradeDTO tradeDTO){
        try{
            if(tradeDTO.getType() == TradeDTO.Type.BUY){
                tradeService.buyStock(tradeDTO);
            }
            else if(tradeDTO.getType() == TradeDTO.Type.SELL){
                tradeService.sellStock(tradeDTO);
            }
            else{
                return ResponseEntity.badRequest().body("Invalid trade type.");
            }
            return ResponseEntity.ok("Trade registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occured while registering the trade");
        }
    }

    @PostMapping(path = "/async", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> tradeAsync(@RequestBody TradeDTO tradeDTO){
        try{
            queueService.send(tradeDTO);
            return ResponseEntity.ok("Trade registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occured while registering the trade");
        }
    }
}
