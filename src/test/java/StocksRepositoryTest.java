import org.example.Main;
import org.example.entities.Stocks;
import org.example.repository.StocksRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Main.class)
public class StocksRepositoryTest {

    @Autowired
    private StocksRepository stocksRepository;

    @Test
    public void testDatabaseConnection() {
        Stocks stock = new Stocks();
        stock.setStockId("test");
        stock.setStockName("Test Stock");
        stock.setOpenPrice(100.0);
        stock.setClosePrice(110.0);
        stock.setHighPrice(120.0);
        stock.setLowPrice(90.0);
        stock.setSettlementPrice(110.0);

        Stocks savedStock = stocksRepository.save(stock);

        Stocks retrievedStock = stocksRepository.findByStockId("test");

        assertThat(retrievedStock).isNotNull();
        assertThat(retrievedStock.getStockName()).isEqualTo(savedStock.getStockName());
        assertThat(retrievedStock.getOpenPrice()).isEqualTo(savedStock.getOpenPrice());
        assertThat(retrievedStock.getClosePrice()).isEqualTo(savedStock.getClosePrice());
        assertThat(retrievedStock.getHighPrice()).isEqualTo(savedStock.getHighPrice());
        assertThat(retrievedStock.getLowPrice()).isEqualTo(savedStock.getLowPrice());
        assertThat(retrievedStock.getSettlementPrice()).isEqualTo(savedStock.getSettlementPrice());
    }
}