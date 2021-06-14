package cz.zdenekvlk.transactions.service;

import com.opencsv.exceptions.CsvException;
import cz.zdenekvlk.transactions.constants.TransactionConstants;
import cz.zdenekvlk.transactions.dto.CsvFile;
import cz.zdenekvlk.transactions.dto.Transaction;
import cz.zdenekvlk.transactions.dto.TransactionCsvFile;
import cz.zdenekvlk.transactions.exception.CsvFileParseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service implements processing transactions from csv file
 *
 * @author Zdenek Vlk <a href="mailto:vlk.zdenek@gmail.com">vlk.zdenek@gmail.com</a>
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class Solution implements SolutionInterface {
    private final CsvFile<Transaction> transactionCsvFile;

    @Override
    public String solution(String location) {
        List<Transaction> Transactions = new ArrayList<>();
        try {
            Transactions = getTransactions(location);
            // Check number of transactions - can be as application properties
            transactionsChecksum(Transactions.size());
            //TODO check no transactions have the same date and time
            //TODO process to set number properly
        } catch (CsvFileParseException | IOException | CsvException e) {
            log.error("Error " + e.getMessage() + " occurred while reading transaction csv file " + location);
            return "";
        }

        log.info(Transactions.toString());

        return "TODO result";

    }

    private List<Transaction> getTransactions(String location) throws IOException, CsvException {
        return transactionCsvFile.readCsvFileIntoBean(
                location, Transaction.class, TransactionConstants.DEFAULT_CSV_SEPARATOR);
    }

    private void transactionsChecksum(int size) throws CsvFileParseException {
        if(!(TransactionCsvFile.MIN_TRANSACTIONS <= size &&
                size <= TransactionCsvFile.MAX_TRANSACTIONS)) {
            log.error("Number of transaction is invalid. Should be between " +
                    TransactionCsvFile.MIN_TRANSACTIONS + " and " + TransactionCsvFile.MAX_TRANSACTIONS);
            throw new CsvFileParseException("Invalid transaction file checksum");
        }
    }
}
