package cz.zdenekvlk.transactions.dto;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class Partner {
    @CsvBindByPosition(position = 1, capture = "(.+)\\/")
    private String name;

    @CsvBindByPosition(position = 2, capture = "\\/(.+)")
    private String phoneNumber;

    private int order;
}
