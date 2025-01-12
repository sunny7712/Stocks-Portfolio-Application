
import org.example.Main;
import org.example.entities.Stocks;
import org.example.service.impl.CSVUpdateStocks;
import org.example.repository.StocksRepository;
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
    private StocksRepository stocksRepository;

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

        Stocks stock = stocksRepository.findByStockId("AATMAJ");
        assertThat(stock).isNotNull();
        assertThat(stock.getStockId()).isEqualTo("AATMAJ");
        assertThat(stock.getOpenPrice()).isGreaterThan(0); // Assuming positive prices in CSV
    }
}