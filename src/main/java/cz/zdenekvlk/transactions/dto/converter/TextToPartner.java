package cz.zdenekvlk.transactions.dto.converter;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.bean.AbstractCsvConverter;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import cz.zdenekvlk.transactions.dto.Partner;

public class TextToPartner<T, I> extends AbstractBeanField<T, I> {
    @Override
    protected Partner convert(String s) {
        Partner partner = new Partner();
        String[] split = s.split("\\/", 2);
        partner.setName(split[0].strip());
        partner.setPhoneNumber(split[1].strip());

        return partner;
    }
}


