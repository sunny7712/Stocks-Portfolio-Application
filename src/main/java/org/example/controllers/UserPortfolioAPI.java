package org.example.controllers;

import org.example.dto.PortfolioDTO;
import org.example.service.UserPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/portfolio")
public class UserPortfolioAPI {

    @Autowired
    private UserPortfolioService userPortfolioService;

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PortfolioDTO> getPortfolioById(@PathVariable("userId") String userId){
        try{
            PortfolioDTO portfolioDTO = userPortfolioService.getPortfolioById(userId);
            if (portfolioDTO == null){
                return ResponseEntity.notFound().build();
            }
            else{
                return ResponseEntity.ok(portfolioDTO);
            }
        } catch (Exception e) {
            return ResponseEntity.status(404).build();
        }
    }
}
