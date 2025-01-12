package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.dto.TradeDTO;
import org.example.entities.Stocks;
import org.example.entities.UserPortfolio;
import org.example.repository.StocksRepository;
import org.example.repository.UserPortfolioRepository;
import org.example.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    UserPortfolioRepository userPortfolioRepository;

    @Autowired
    StocksRepository stocksRepository;

    @Transactional
    @Override
    public void buyStock(TradeDTO tradeDTO){
        validateTradeDTO(tradeDTO);
        String userId = tradeDTO.getUserId();
        String stockId = tradeDTO.getStockId();
        int quantity = tradeDTO.getQuantity();
        Optional<UserPortfolio> optionalUserPortfolio = userPortfolioRepository.findByUserIdAndStockId(userId, stockId);
        Double buyPrice = stocksRepository.findByStockId(stockId).getClosePrice();
        UserPortfolio portfolio;
        if(optionalUserPortfolio.isPresent()){
            portfolio = optionalUserPortfolio.get();
            portfolio.setQuantity(portfolio.getQuantity() + quantity);
            portfolio.setBuyPrice(buyPrice);
        }
        else{
            Stocks stocks = stocksRepository.findByStockId(stockId);
            portfolio = UserPortfolio.builder()
                    .userId(userId)
                    .stockId(stockId)
                    .stocks(stocks)
                    .quantity(quantity)
                    .buyPrice(buyPrice).build();
        }
        userPortfolioRepository.save(portfolio);
    }

    @Transactional
    @Override
    public void sellStock(TradeDTO tradeDTO){
        validateTradeDTO(tradeDTO);
        String userId = tradeDTO.getUserId();
        String stockId = tradeDTO.getStockId();
        int quantity = tradeDTO.getQuantity();
        Optional<UserPortfolio> optionalUserPortfolio = userPortfolioRepository.findByUserIdAndStockId(userId, stockId);
        if (optionalUserPortfolio.isEmpty()) {
            throw new IllegalStateException("Cannot sell stock that the user does not own.");
        }
        UserPortfolio portfolio = optionalUserPortfolio.get();
        if (portfolio.getQuantity() < quantity) {
            throw new IllegalArgumentException("Sell quantity exceeds available stock holdings.");
        }
        portfolio.setQuantity(portfolio.getQuantity() - quantity);

        if(portfolio.getQuantity() == 0){
            userPortfolioRepository.delete(portfolio);
        }
        else{
            userPortfolioRepository.save(portfolio);
        }
    }

    private void validateTradeDTO(TradeDTO tradeDTO) {
        if (tradeDTO.getQuantity() <= 0) {
            throw new IllegalArgumentException("Trade quantity must be greater than zero.");
        }
    }

}
