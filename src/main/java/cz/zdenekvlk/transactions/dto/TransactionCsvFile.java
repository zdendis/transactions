package cz.zdenekvlk.transactions.dto;

public class TransactionCsvFile implements CsvFile<Transaction> {
    public static long MIN_TRANSACTIONS = 1;
    public static long MAX_TRANSACTIONS = 100;
    public static short MIN_YEAR = 2000;
    public static short MAX_YEAR = 2020;
    public static short MIN_TRANSACTION_NAME_CHARS = 1;
    public static short MAX_TRANSACTION_NAME_CHARS = 200;
    public static short MIN_PARTNER_NAME_CHARS = 1;
    public static short MAX_PARTNER_NAME_CHARS = 30;

}
