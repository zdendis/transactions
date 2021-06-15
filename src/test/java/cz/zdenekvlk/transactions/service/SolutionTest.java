package cz.zdenekvlk.transactions.service;

import cz.zdenekvlk.transactions.dto.TransactionCsvFile;
import cz.zdenekvlk.transactions.dto.processor.TransactionLineCounter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { Solution.class, TransactionCsvFile.class, TransactionLineCounter.class })
class SolutionTest {
    private static String TEST_FILE_LOCATION = "transactions/transactions.csv";
    private static Resource resourceFile;

    @Autowired
    private SolutionInterface solutionInterface;

    @BeforeAll
    public static void init() {
        resourceFile = new ClassPathResource(TEST_FILE_LOCATION);
    }

    @Test
    @DisplayName("OK test - file is processed successful")
    void solutionOk() throws IOException {
        log.info(System.lineSeparator() + solutionInterface.solution(resourceFile.getFile().getPath()));
        log.info(System.lineSeparator() + solutionInterface.solution(resourceFile.getFile().getPath()));
        log.info(System.lineSeparator() + solutionInterface.solution(resourceFile.getFile().getPath()));

        String result = solutionInterface.solution(resourceFile.getFile().getPath());
        String[] resultLines = result.split(System.lineSeparator());

        assertEquals("Netflix|02|payment weekly", resultLines[0]);
        assertEquals("Netflix|10|recharging of 987654321", resultLines[14]);

    }
}