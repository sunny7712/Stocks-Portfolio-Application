package org.example.repository;

import org.example.entities.Stocks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StocksRepository extends JpaRepository<Stocks, Long> {

    Stocks findByStockId(String stockId);
}