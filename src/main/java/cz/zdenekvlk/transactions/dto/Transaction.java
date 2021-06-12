package cz.zdenekvlk.transactions.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Transaction implements CsvFileLine {
    @CsvBindByPosition(position = 0)
    private String name;

    @CsvBindByPosition(position = 1, capture = "^(.+?)\\/")
    private String partnerName;

    @CsvBindByPosition(position = 1, capture = "\\/(.+)")
    private String phoneNumber;

    @CsvBindByPosition(position = 2)
    @CsvDate(value = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime date;
}
