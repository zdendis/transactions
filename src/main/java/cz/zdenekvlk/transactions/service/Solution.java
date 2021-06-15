package cz.zdenekvlk.transactions.service;

import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvException;
import cz.zdenekvlk.transactions.constants.TransactionConstants;
import cz.zdenekvlk.transactions.dto.*;
import cz.zdenekvlk.transactions.exception.CsvFileParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Service implements processing transactions from csv file
 *
 * @author Zdenek Vlk <a href="mailto:vlk.zdenek@gmail.com">vlk.zdenek@gmail.com</a>
 */

@Slf4j
@Service
public class Solution implements SolutionInterface {
    private final CsvFile<Transaction> transactionCsvFile;

    public Solution(CsvFile<Transaction> transactionCsvFile) {
        this.transactionCsvFile = transactionCsvFile;
    }

    @Override
    public String solution(String location) {
        List<Transaction> transactions;
        try {
            transactions = getTransactions(location);
            // Check number of transactions - can be as application properties
            transactionsChecksum(transactions.size());
        } catch (CsvFileParseException | IOException | CsvException e) {
            log.error("Error " + e.getMessage() + " occurred while reading transaction csv file " + location);
            return "";
        }

        return createResultString(transactions);
    }

    private List<Transaction> getTransactions(String location) throws IOException, CsvException {
        List<Transaction> transactions = transactionCsvFile.readCsvFileIntoBean(
                location, Transaction.class, TransactionConstants.DEFAULT_CSV_SEPARATOR);

        transactions.forEach(this::setTransactionNumber);

        return transactions;
    }

    private String createTransactionNumber(int number, int transactionCount) {
        String trxNumber = String.valueOf(number);
        if(transactionCount < 10) {
            return trxNumber;
        } else if((transactionCount < 100 && number < 10) || (transactionCount == 100 && number >= 10)) {
            return "0" + trxNumber;
        } else if(transactionCount == 100){
            return "00" + trxNumber;
        } else {
            return trxNumber;
        }
    }

    private void transactionsChecksum(int size) throws CsvFileParseException {
        if(!(TransactionCsvFile.MIN_TRANSACTIONS <= size &&
                size <= TransactionCsvFile.MAX_TRANSACTIONS)) {
            log.error("Number of transaction is invalid. Should be between " +
                    TransactionCsvFile.MIN_TRANSACTIONS + " and " + TransactionCsvFile.MAX_TRANSACTIONS);
            throw new CsvFileParseException("Invalid transaction file checksum");
        }
    }

    private String createResultString(List<Transaction> transactions) {
        StringBuilder stringBuilder = new StringBuilder();
        transactions.forEach(
                transaction -> {
                    stringBuilder.append(transaction.getPartner().getName());
                    stringBuilder.append(TransactionConstants.RESULT_SEPARATOR);
                    stringBuilder.append(transaction.getTransactionNumber());
                    stringBuilder.append(TransactionConstants.RESULT_SEPARATOR);
                    stringBuilder.append(transaction.getName());
                    stringBuilder.append(System.lineSeparator());

                }
        );

        return stringBuilder.toString();
    }

    private void setTransactionNumber(Transaction transaction) {
        String partnerName = transaction.getPartner().getName();
        int partnerTransactionsCount = transactionCsvFile.getLineCounter().getTransactionCount(partnerName);
        List<TransactionKey> sortedByDateTransactions = transactionCsvFile.getLineCounter().getTransactions(partnerName);
        Collections.sort(sortedByDateTransactions);

        int partnerTransactionNumber = sortedByDateTransactions.indexOf(
                new TransactionKey(transaction.getName(), transaction.getDateTime())) + 1;

        transaction.setTransactionNumber(createTransactionNumber(partnerTransactionNumber, partnerTransactionsCount));
    }
}
