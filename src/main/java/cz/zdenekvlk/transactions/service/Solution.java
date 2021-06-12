package cz.zdenekvlk.transactions.service;

import cz.zdenekvlk.transactions.dto.CsvFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service implements processing transactions from csv file
 *
 * @author Zdenek Vlk <a href="mailto:vlk.zdenek@gmail.com">vlk.zdenek@gmail.com</a>
 */

@Slf4j
@Service
public class Solution implements SolutionInterface {

    @Override
    public String solution(String location) {
        System.out.println(location);
        return location;

    }


    private List<CsvFile> readFile(String location) {
        List<CsvFile> lines = new ArrayList<>();

//        try {
//
//            lines = FileUtils.readCsvFile(Paths.get(location), TransactionLine.class, TransactionConstants.DEFAULT_CSV_SEPARATOR);
//        } catch (IOException e) {
//            log.error("Error occurred while reading file " + location);
//            log.error(e.getMessage());
//        }

        return lines;
    }

//    TODO private processList

}
