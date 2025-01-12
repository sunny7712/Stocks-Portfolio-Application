package org.example.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PortfolioDTO {
    private List<HoldingsDTO> holdingsDTOList;
    private Double totalPortfolioHolding;
    private Double totalBuyPrice;
    private Double profitLoss;
    private Double profitLossPercentage;
}
