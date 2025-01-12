package org.example.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPortfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String stockId;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Stocks stocks;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private Double buyPrice;
}
