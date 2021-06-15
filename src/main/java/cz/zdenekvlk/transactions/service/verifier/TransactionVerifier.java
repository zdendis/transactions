package cz.zdenekvlk.transactions.service.verifier;

import com.opencsv.bean.BeanVerifier;
import com.opencsv.exceptions.CsvConstraintViolationException;
import cz.zdenekvlk.transactions.dto.Transaction;
import cz.zdenekvlk.transactions.dto.TransactionCsvFile;

public class TransactionVerifier implements BeanVerifier<Transaction> {
    @Override
    public boolean verifyBean(Transaction transaction) throws CsvConstraintViolationException {
        if(!isTransactionNameValid(transaction.getName())) {
            throw new CsvConstraintViolationException(transaction, "Transaction name " + transaction.getName() + " is invalid");
        }

        if(!isPartnerNameValid(transaction.getPartner().getName())) {
            throw new CsvConstraintViolationException(transaction, "Partner name " + transaction.getPartner().getName() + " is invalid");
        }

        if(!isTransactionYearInRange(transaction.getDateTime().getYear())) {
            throw new CsvConstraintViolationException(transaction, "Year of transaction " + transaction.getDateTime().getYear() + " is invalid");
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
