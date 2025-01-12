package org.example.service.impl;

import org.example.dto.HoldingsDTO;
import org.example.dto.PortfolioDTO;
import org.example.entities.Stocks;
import org.example.entities.UserPortfolio;
import org.example.repository.StocksRepository;
import org.example.repository.UserPortfolioRepository;
import org.example.service.UserPortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserPortfolioServiceImpl implements UserPortfolioService {

    @Autowired
    private UserPortfolioRepository userPortfolioRepository;

    @Autowired
    private StocksRepository stocksRepository;

    private HoldingsDTO mapHoldingsToDTO(UserPortfolio userPortfolio){
        Stocks stocks = userPortfolio.getStocks();
        if (stocks == null) {
            throw new IllegalStateException("Portfolio entry has no associated stock.");
        }
        String stockName = stocks.getStockName();
        String stockId = stocks.getStockId();
        int quantity = userPortfolio.getQuantity();
        Double buyPrice = userPortfolio.getBuyPrice();
        Double currentPrice = stocksRepository.findByStockId(stockId).getClosePrice();
        Double gainLoss = (currentPrice - userPortfolio.getBuyPrice()) * userPortfolio.getQuantity();

        return HoldingsDTO.builder()
                .stockName(stockName)
                .stockId(stockId)
                .quantity(quantity)
                .buyPrice(buyPrice)
                .currentPrice(currentPrice)
                .gainLoss(gainLoss).build();
    }

    @Override
    public PortfolioDTO getPortfolioById(String userId){

        List<UserPortfolio> portfolioList = userPortfolioRepository.findAllStocksOfUser(userId);
        if (portfolioList.isEmpty()) {
            return PortfolioDTO.builder()
                    .holdingsDTOList(Collections.emptyList())
                    .totalPortfolioHolding(0.0)
                    .totalBuyPrice(0.0)
                    .profitLoss(0.0)
                    .profitLossPercentage(0.0)
                    .build();
        }
        List<HoldingsDTO> holdingsDTOList = portfolioList.stream()
                .map(this::mapHoldingsToDTO)
                .toList();

        Double totalPortfolioHolding = getTotalPortfolioHolding(holdingsDTOList);

        Double totalBuyPrice = getTotalBuyPrice(holdingsDTOList);

        Double profitLoss = getProfitLoss(totalPortfolioHolding, totalBuyPrice);

        Double profitLossPercentage = getProfitLossPercentage(totalBuyPrice, profitLoss);

        return PortfolioDTO.builder()
                .holdingsDTOList(holdingsDTOList)
                .totalPortfolioHolding(totalPortfolioHolding)
                .totalBuyPrice(totalBuyPrice)
                .profitLoss(profitLoss)
                .profitLossPercentage(profitLossPercentage)
                .build();
    }

    private static Double getProfitLossPercentage(Double totalBuyPrice, Double profitLoss) {
        return totalBuyPrice > 0 ? ((profitLoss / totalBuyPrice) * 100.0) : 0;
    }

    private static Double getProfitLoss(Double totalPortfolioHolding, Double totalBuyPrice) {
        return totalPortfolioHolding - totalBuyPrice;
    }

    private static Double getTotalBuyPrice(List<HoldingsDTO> holdingsDTOList) {
        return holdingsDTOList.stream()
                .mapToDouble(holding -> holding.getBuyPrice() * holding.getQuantity()).sum();
    }

    private static Double getTotalPortfolioHolding(List<HoldingsDTO> holdingsDTOList) {
        return holdingsDTOList.stream()
                .mapToDouble(holding -> holding.getCurrentPrice() * holding.getQuantity()).sum();
    }
}
