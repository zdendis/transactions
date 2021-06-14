package cz.zdenekvlk.transactions.dto;

import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBeanBuilder;
import cz.zdenekvlk.transactions.dto.processor.LineCounter;
import cz.zdenekvlk.transactions.dto.processor.TransactionLineCounter;
import cz.zdenekvlk.transactions.dto.processor.PartnerTransactionCounterProcessor;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RequiredArgsConstructor
public class TransactionCsvFile implements CsvFile<Transaction> {
    private final TransactionLineCounter transactionLineCounter;

    public static int MIN_TRANSACTIONS = 1;
    public static int MAX_TRANSACTIONS = 100;
    public static int MIN_YEAR = 2000;
    public static int MAX_YEAR = 2020;
    public static int MIN_TRANSACTION_NAME_CHARS = 1;
    public static int MAX_TRANSACTION_NAME_CHARS = 200;
    public static int MIN_PARTNER_NAME_CHARS = 1;
    public static int MAX_PARTNER_NAME_CHARS = 30;

    @Override
    public List<Transaction> readCsvFileIntoBean(String location, Class<Transaction> type, char csvSeparator) throws IOException {
        return new CsvToBeanBuilder<Transaction>(
                new CSVReaderBuilder(
                        Files.newBufferedReader(Paths.get(location)))
                        .withRowProcessor(new PartnerTransactionCounterProcessor(transactionLineCounter))
                        .build())
                .withType(type)
                .withIgnoreLeadingWhiteSpace(true)
                .withOrderedResults(true)
                .withSeparator(csvSeparator)
                .build()
                .parse();
    }

    @Override
    public LineCounter getLineCounter() {
        return transactionLineCounter;
    }
}
