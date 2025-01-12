package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HoldingsDTO {
    private String stockName;
    private String stockId;
    private Integer quantity;
    private Double buyPrice;
    private Double currentPrice;
    private Double gainLoss;
}
