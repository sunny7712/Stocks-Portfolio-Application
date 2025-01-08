package org.example.repository;

import org.example.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    Stock findByStockName(String stockName);

    Stock findByStockId(Long stockId);
}
