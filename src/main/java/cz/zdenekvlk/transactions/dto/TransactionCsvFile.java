package cz.zdenekvlk.transactions.dto;

import org.springframework.stereotype.Component;

@Component
public class TransactionCsvFile implements CsvFile<Transaction> {
    public static int MIN_TRANSACTIONS = 1;
    public static int MAX_TRANSACTIONS = 100;
    public static int MIN_YEAR = 2000;
    public static int MAX_YEAR = 2020;
    public static int MIN_TRANSACTION_NAME_CHARS = 1;
    public static int MAX_TRANSACTION_NAME_CHARS = 200;
    public static int MIN_PARTNER_NAME_CHARS = 1;
    public static int MAX_PARTNER_NAME_CHARS = 30;

}
