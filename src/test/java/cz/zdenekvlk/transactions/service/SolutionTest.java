package cz.zdenekvlk.transactions.service;

import cz.zdenekvlk.transactions.dto.TransactionCsvFile;
import cz.zdenekvlk.transactions.dto.processor.TransactionLineCounter;
import cz.zdenekvlk.transactions.service.verifier.TransactionVerifier;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { Solution.class, TransactionCsvFile.class, TransactionLineCounter.class })
class SolutionTest {
    private static String TEST_FILE_LOCATION = "transactions/transactions.csv";
    private static String TEST_INCONSISTENT_FILE_LOCATION = "transactions/inconsistent_transactions.csv";
    private static String IS_INVALID = "is invalid";
    private static Resource resourceFile;
    private static Resource inconsistentResourceFile;

    @Autowired
    private SolutionInterface solutionInterface;

    @BeforeAll
    public static void init() throws IOException {
        resourceFile = new ClassPathResource(TEST_FILE_LOCATION);
        File rFile = resourceFile.getFile();

        inconsistentResourceFile = new ClassPathResource(TEST_INCONSISTENT_FILE_LOCATION);
    }

    @Test
    @DisplayName("OK test - file is processed successful")
    void solutionOk() throws IOException {
        String result = solutionInterface.solution(resourceFile.getFile().getPath());
        log.info(System.lineSeparator() + result);

        String[] resultLines = result.split(System.lineSeparator());

        assertEquals("Netflix|02|payment weekly", resultLines[0]);
        assertEquals("Netflix|10|recharging of 987654321", resultLines[14]);

    }

    @Test
    @DisplayName("NOK test - file is inconsistent / partner name too large")
    void solutionCsvFileDataValidationPartnerName() throws IOException {
        String result = solutionInterface.solution(inconsistentResourceFile.getFile().getPath());

        assertTrue(result.contains(TransactionVerifier.PARTNER_NAME));
        assertTrue(result.contains(IS_INVALID));
    }

    @Test
    @DisplayName("NOK test - file is inconsistent / transaction name too large")
    void solutionCsvFileDataValidationTransactionName(@TempDir Path tempDir) throws IOException {
        Path inconsistentTransactionFileLocation = tempDir.resolve("inconsistent.csv");

        Files.write(inconsistentTransactionFileLocation,
                Arrays.asList("yearly subscription and montly yearly subscription and montly yearly subscription and " +
                        "montly yearly subscription and montly yearly subscription and montly yearly subscription" +
                        " and montly yearly subscription and montly, Netflix /602602602, 2016-01-02 10:55:32"));

        String result = solutionInterface.solution(inconsistentTransactionFileLocation.toString());

        assertTrue(result.contains(TransactionVerifier.TRANSACTION_NAME));
        assertTrue(result.contains(IS_INVALID));
    }

    @Test
    @DisplayName("NOK test - file is inconsistent / year of transaction not in requested range")
    void solutionCsvFileDataValidationTransactionYear(@TempDir Path tempDir) throws IOException {
        Path inconsistentTransactionFileLocation = tempDir.resolve("inconsistent.csv");

        Files.write(inconsistentTransactionFileLocation,
                Arrays.asList("yearly subscription, Netflix /602602602, " + (TransactionCsvFile.MIN_YEAR - 1) + "-01-02 10:55:32"));

        String result = solutionInterface.solution(inconsistentTransactionFileLocation.toString());

        assertTrue(result.contains(TransactionVerifier.YEAR_OF_TRANSACTION));
        assertTrue(result.contains(IS_INVALID));
    }


}