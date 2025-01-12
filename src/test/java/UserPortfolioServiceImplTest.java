import org.example.dto.PortfolioDTO;
import org.example.entities.Stocks;
import org.example.entities.UserPortfolio;
import org.example.repository.StocksRepository;
import org.example.repository.UserPortfolioRepository;
import org.example.service.impl.UserPortfolioServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class UserPortfolioServiceImplTest {


    @Mock
    private UserPortfolioRepository userPortfolioRepository;

    @Mock
    private StocksRepository stocksRepository;

    @InjectMocks
    private UserPortfolioServiceImpl userPortfolioServiceImpl;


    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getPortfolioById_emptyPortfolio_returnsEmptyDTO() {
        when(userPortfolioRepository.findAllStocksOfUser("user1")).thenReturn(Collections.emptyList());

        PortfolioDTO portfolioDTO = userPortfolioServiceImpl.getPortfolioById("user1");

        assertEquals(0.0, portfolioDTO.getTotalPortfolioHolding());
        assertEquals(0.0, portfolioDTO.getTotalBuyPrice());
        assertEquals(0.0, portfolioDTO.getProfitLoss());
        assertEquals(0.0, portfolioDTO.getProfitLossPercentage());
        assertTrue(portfolioDTO.getHoldingsDTOList().isEmpty());
    }

    @Test
    void getPortfolioById_nonEmptyPortfolio_returnsCorrectValues() {
        Stocks stock = new Stocks(1L, "STK1", "Stock One", 100.0, 110.0, 90.0, 120.0, 110.0);
        UserPortfolio portfolio = new UserPortfolio(1L, "user1", "STK1", stock, 10, 100.0);

        when(userPortfolioRepository.findAllStocksOfUser("user1")).thenReturn(List.of(portfolio));
        when(stocksRepository.findByStockId("STK1")).thenReturn(stock);

        PortfolioDTO portfolioDTO = userPortfolioServiceImpl.getPortfolioById("user1");

        assertEquals(1100.0, portfolioDTO.getTotalPortfolioHolding());
        assertEquals(1000.0, portfolioDTO.getTotalBuyPrice());
        assertEquals(100.0, portfolioDTO.getProfitLoss());
        assertEquals(10.0, portfolioDTO.getProfitLossPercentage());
    }
}
