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

        if(!isPartnerNameValid(line.getPartnerName())) {
            throw new CsvFileParseException("Partner name " + line.getPartnerName() + " is invalid");
        }
    }

    private boolean isTransactionNameValid(String name) {
//        TransactionCsvFile.MIN_TRANSACTION_NAME_CHARS
//        TransactionCsvFile.MAX_TRANSACTION_NAME_CHARS
        return true;

    }

    private boolean isPartnerNameValid(String partnerName) {
//        TransactionCsvFile.MIN_PARTNER_NAME_CHARS
//        TransactionCsvFile.MAX_PARTNER_NAME_CHARS
        return true;
    }

    private boolean isTransactionYearInRange() {
//        TransactionCsvFile.MIN_YEAR
//        TransactionCsvFile.MAX_YEAR
        return true;
    }
}
