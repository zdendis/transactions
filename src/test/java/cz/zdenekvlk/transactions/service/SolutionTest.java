package cz.zdenekvlk.transactions.service;

import cz.zdenekvlk.transactions.dto.CsvFile;
import cz.zdenekvlk.transactions.dto.Transaction;
import cz.zdenekvlk.transactions.dto.TransactionCsvFile;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

//@ExtendWith(MockitoExtension.class)
class SolutionTest {
    private static String TEST_FILE_LOCATION = "transactions/transactions.csv";

    private static Resource resourceFile;

    @BeforeAll
    public static void init() {
        resourceFile = new ClassPathResource(TEST_FILE_LOCATION);
    }

//    @InjectMocks
    private Solution solution = new Solution(new TransactionCsvFile());

//    @Mock
    private CsvFile<Transaction> transactionCsvFile;

    @Test
    @DisplayName("OK test - file is processed successful")
    void solutionOk() throws IOException {
        solution.solution(resourceFile.getFile().getPath());
    }
}