package org.example.repository;

import org.example.entities.UserPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserPortfolioRepository extends JpaRepository<UserPortfolio, Long> {

    Optional<UserPortfolio> findByUserIdAndStockId(String userId, String stockId);

    @Query("SELECT p from UserPortfolio p JOIN FETCH p.stocks WHERE p.userId = :userId AND p.quantity > 0")
    List<UserPortfolio> findAllStocksOfUser(@Param("userId") String userId);
}
