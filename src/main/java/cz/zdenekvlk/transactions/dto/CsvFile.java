package cz.zdenekvlk.transactions.dto;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import cz.zdenekvlk.transactions.dto.processor.LineCounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public interface CsvFile<T extends CsvFileLine> {
    default List<String[]> readCsvFile(String location, char csvSeparator) throws IOException, CsvException {
        return new CSVReader(Files.newBufferedReader(Paths.get(location))).readAll();
    }

    default List<T> readCsvFileIntoBean(String location, Class<T> type, char csvSeparator) throws IOException {
        return new CsvToBeanBuilder<T>(Files.newBufferedReader(Paths.get(location)))
                .withType(type)
                .withIgnoreLeadingWhiteSpace(true)
                .withOrderedResults(true)
                .withSeparator(csvSeparator)
                .build()
                .parse();
    }

    LineCounter getLineCounter();
}
