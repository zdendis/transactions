package cz.zdenekvlk.transactions.service;

import cz.zdenekvlk.transactions.dto.TransactionCsvFile;
import cz.zdenekvlk.transactions.dto.processor.TransactionLineCounter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Slf4j
class SolutionTest {
    private static String TEST_FILE_LOCATION = "transactions/transactions.csv";

    private static Resource resourceFile;

    @BeforeAll
    public static void init() {
        resourceFile = new ClassPathResource(TEST_FILE_LOCATION);
    }

    private Solution solution = new Solution(new TransactionCsvFile(new TransactionLineCounter()));

    @Test
    @DisplayName("OK test - file is processed successful")
    void solutionOk() throws IOException {
        log.info(solution.solution(resourceFile.getFile().getPath()));
    }
}