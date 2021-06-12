package cz.zdenekvlk.transactions.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

class SolutionTest {
    private static String TEST_FILE_PATH = "transactions/transactions.csv";

    @Mock
    private SolutionInterface solutionInterface;

    @BeforeAll
    void setUp() {
        solutionInterface = new Solution();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("OK test - file is processed successful")
    void solutionOk() {

    }
}