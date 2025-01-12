package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockDTO {
    private String stockId;
    private String stockName;
    private Double openPrice;
    private Double closePrice;
    private Double highPrice;
    private Double lowPrice;
    private Double settlementPrice;
}
