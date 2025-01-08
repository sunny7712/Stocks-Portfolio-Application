
import org.example.Main;
import org.example.entities.Stock;
import org.example.service.CSVUpdateStocks;
import org.example.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Main.class)
public class CSVUpdateStocksTest {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private CSVUpdateStocks csvUpdateStocks;

    private MultipartFile file;

    @BeforeEach
    public void readFile() throws IOException {
        ClassPathResource classPathResource = new ClassPathResource("sme070125.csv");
        file = new MockMultipartFile("file", classPathResource.getFilename(), "text/csv", classPathResource.getInputStream());

    }

    @Test
    public void testUpdateStocksFromCSV() throws IOException {

        csvUpdateStocks.updateStocks(file);

        Stock stock = stockRepository.findByStockName("AATMAJ HEALTHCARE LIMITED");
        assertThat(stock).isNotNull();
        assertThat(stock.getStockName()).isEqualTo("AATMAJ HEALTHCARE LIMITED");
        assertThat(stock.getOpenPrice()).isGreaterThan(0); // Assuming positive prices in CSV
    }
}