package cz.zdenekvlk.transactions.service.validator;

import cz.zdenekvlk.transactions.dto.Transaction;
import cz.zdenekvlk.transactions.dto.TransactionCsvFile;
import cz.zdenekvlk.transactions.exception.CsvFileParseException;

public class TransactionLineValidator implements CsvFileLineValidator<Transaction>{
    @Override
    public void validate(Transaction line) throws CsvFileParseException {
        if(!isTransactionNameValid(line.getName())) {
            throw new CsvFileParseException("Transaction name " + line.getName() + " is invalid");
        }

        if(!isPartnerNameValid(line.getPartner().getName())) {
            throw new CsvFileParseException("Partner name " + line.getPartner().getName() + " is invalid");
        }

//        if(!isTransactionYearInRange(line.getDateTime().getYear())) {
//            throw new CsvFileParseException("Year of transaction " + line.getDateTime().getYear() + " is invalid");
//        }
    }

    private boolean isTransactionNameValid(String name) {
        return (name != null &&
                TransactionCsvFile.MIN_TRANSACTION_NAME_CHARS <= name.strip().length() &&
                name.length() >= TransactionCsvFile.MAX_TRANSACTION_NAME_CHARS);
    }

    private boolean isPartnerNameValid(String partnerName) {
        return (partnerName != null &&
                TransactionCsvFile.MIN_PARTNER_NAME_CHARS <= partnerName.strip().length() &&
                partnerName.length() >= TransactionCsvFile.MAX_PARTNER_NAME_CHARS);
    }

    private boolean isTransactionYearInRange(int year) {
        return (TransactionCsvFile.MIN_YEAR <= year && year <= TransactionCsvFile.MAX_YEAR);
    }
}
