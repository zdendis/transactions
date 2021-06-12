package cz.zdenekvlk.transactions.service.validator;

import cz.zdenekvlk.transactions.dto.CsvFileLine;
import cz.zdenekvlk.transactions.exception.CsvFileParseException;

public interface CsvFileLineValidator<T extends CsvFileLine> {
    void validate(T line) throws CsvFileParseException;
}

