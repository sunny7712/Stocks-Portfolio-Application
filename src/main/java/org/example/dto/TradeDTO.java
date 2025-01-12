package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeDTO {
    private String userId;
    public enum Type{
        BUY,
        SELL
    };
    private Type type;
    private Integer quantity;
    private String stockId;
}
