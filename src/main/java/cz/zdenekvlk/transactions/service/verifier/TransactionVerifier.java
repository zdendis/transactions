package cz.zdenekvlk.transactions.service.verifier;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.exceptions.CsvConstraintViolationException;
import cz.zdenekvlk.transactions.dto.Transaction;
import cz.zdenekvlk.transactions.dto.TransactionCsvFile;

public class TransactionVerifier implements BeanVerifier<Transaction> {
    public static String YEAR_OF_TRANSACTION = "Year of transaction ";
    public static String PARTNER_NAME = "Partner name ";
    public static String TRANSACTION_NAME = "Transaction name ";


    @Override
    public boolean verifyBean(Transaction transaction) throws CsvConstraintViolationException {
        if(!isTransactionNameValid(transaction.getName())) {
            throw new CsvConstraintViolationException(transaction, TRANSACTION_NAME + transaction.getName() + " is invalid");
        }

        if(!isPartnerNameValid(transaction.getPartner().getName())) {
            throw new CsvConstraintViolationException(transaction, PARTNER_NAME + transaction.getPartner().getName() + " is invalid");
        }

        if(!isTransactionYearInRange(transaction.getDateTime().getYear())) {
            throw new CsvConstraintViolationException(transaction, YEAR_OF_TRANSACTION + transaction.getDateTime().getYear() + " is invalid");
        }

        return true;
    }

    private boolean isTransactionNameValid(String name) {
        return (name != null &&
                TransactionCsvFile.MIN_TRANSACTION_NAME_CHARS <= name.length() &&
                name.length() <= TransactionCsvFile.MAX_TRANSACTION_NAME_CHARS);
    }

    private boolean isPartnerNameValid(String partnerName) {
        return (partnerName != null &&
                TransactionCsvFile.MIN_PARTNER_NAME_CHARS <= partnerName.strip().length() &&
                partnerName.length() <= TransactionCsvFile.MAX_PARTNER_NAME_CHARS);
    }

    private boolean isTransactionYearInRange(int year) {
        return (TransactionCsvFile.MIN_YEAR <= year && year <= TransactionCsvFile.MAX_YEAR);
    }
}
