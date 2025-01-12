
import org.example.dto.TradeDTO;
import org.example.entities.Stocks;
import org.example.entities.UserPortfolio;
import org.example.repository.StocksRepository;
import org.example.repository.UserPortfolioRepository;
import org.example.service.impl.TradeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.example.dto.TradeDTO.Type.BUY;
import static org.example.dto.TradeDTO.Type.SELL;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TradeServiceTest {

    @Mock
    private UserPortfolioRepository userPortfolioRepository;

    @Mock
    private StocksRepository stocksRepository;

    @InjectMocks
    private TradeServiceImpl tradeServiceImpl;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buyStock_newPortfolio_createsPortfolio() {
        TradeDTO tradeDTO = new TradeDTO("user1", BUY, 10, "STK1");
        Stocks stock = new Stocks(1L, "STK1", "Stock One", 100.0, 110.0, 90.0, 120.0, 110.0);

        when(userPortfolioRepository.findByUserIdAndStockId("user1", "STK1")).thenReturn(Optional.empty());
        when(stocksRepository.findByStockId("STK1")).thenReturn(stock);

        tradeServiceImpl.buyStock(tradeDTO);

        verify(userPortfolioRepository, times(1)).save(any(UserPortfolio.class));
    }

    @Test
    void sellStock_insufficientQuantity_throwsException() {
        TradeDTO tradeDTO = new TradeDTO("user1", SELL, 5, "STK1");
        UserPortfolio portfolio = new UserPortfolio(1L, "user1", "STK1", new Stocks(), 3, 100.0);

        when(userPortfolioRepository.findByUserIdAndStockId("user1", "STK1")).thenReturn(Optional.of(portfolio));

        assertThrows(IllegalArgumentException.class, () -> tradeServiceImpl.sellStock(tradeDTO));
    }
}
