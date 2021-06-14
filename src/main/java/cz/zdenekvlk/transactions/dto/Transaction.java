package cz.zdenekvlk.transactions.dto;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByPosition;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvIgnore;
import cz.zdenekvlk.transactions.dto.converter.TextToPartner;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Transaction implements CsvFileLine {
    @CsvBindByPosition(position = 0)
    private String name;

    @CsvCustomBindByPosition(position = 1, converter = TextToPartner.class)
    private Partner partner;

    @CsvBindByPosition(position = 2, capture = "^[\\s]*(.*)$")
    @CsvDate(value = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateTime;

    private String transactionNumber;
}
