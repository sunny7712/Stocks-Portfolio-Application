package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockInfo {
    private Long stockId;
    private String stockName;
    private Double openPrice;
    private Double closePrice;
    private Double lowPrice;
    private Double highPrice;
    private Double settlementPrice; // currently considering it same as closing price;
}
