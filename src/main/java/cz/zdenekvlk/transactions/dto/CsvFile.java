package cz.zdenekvlk.transactions.dto;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public interface CsvFile<T extends CsvFileLine> {
    default List<String[]> readCsvFile(Path path, char csvSeparator) throws IOException, CsvException {
        return new CSVReader(Files.newBufferedReader(path)).readAll();
    }

    default List<T> readCsvFileIntoBean(Path path, Class<T> type, char csvSeparator) throws IOException {
        ColumnPositionMappingStrategy<T> ms = new ColumnPositionMappingStrategy<>();
        ms.setType(type);

        Reader reader = Files.newBufferedReader(path);
        CsvToBean<T> cb = new CsvToBeanBuilder<T>(reader)
                .withType(type)
                .withSeparator(csvSeparator)
                .withMappingStrategy(ms)
                .build();

        List<T> list = cb.parse();
        reader.close();
        return list;
    }
}
