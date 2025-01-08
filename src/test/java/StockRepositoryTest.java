import org.example.Main;
import org.example.entities.Stock;
import org.example.repository.StockRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Main.class)
public class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    public void testDatabaseConnection() {
        // Create a stock object
        Stock stock = new Stock();
        stock.setStockName("Test Stock");
        stock.setOpenPrice(100.0);
        stock.setClosePrice(110.0);
        stock.setHighPrice(120.0);
        stock.setLowPrice(90.0);
        stock.setSettlementPrice(110.0);

        // Save the stock object to the database
        Stock savedStock = stockRepository.save(stock);

        // Retrieve the stock object from the database by its name
        Stock retrievedStock = stockRepository.findByStockName("Test Stock");

        // Verify that the saved and retrieved objects match
        assertThat(retrievedStock).isNotNull();
        assertThat(retrievedStock.getStockName()).isEqualTo(savedStock.getStockName());
        assertThat(retrievedStock.getOpenPrice()).isEqualTo(savedStock.getOpenPrice());
        assertThat(retrievedStock.getClosePrice()).isEqualTo(savedStock.getClosePrice());
        assertThat(retrievedStock.getHighPrice()).isEqualTo(savedStock.getHighPrice());
        assertThat(retrievedStock.getLowPrice()).isEqualTo(savedStock.getLowPrice());
        assertThat(retrievedStock.getSettlementPrice()).isEqualTo(savedStock.getSettlementPrice());
    }
}