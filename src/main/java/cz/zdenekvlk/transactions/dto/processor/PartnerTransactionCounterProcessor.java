package cz.zdenekvlk.transactions.dto.processor;

import com.opencsv.processor.RowProcessor;
import cz.zdenekvlk.transactions.dto.TransactionKey;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
public class PartnerTransactionCounterProcessor implements RowProcessor {
    private final TransactionLineCounter transactionLineCounter;

    @Override
    public String processColumnItem(String column) {
        Objects.requireNonNull(column);
        return column.strip();
    }

    @Override
    public void processRow(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            strings[i] = processColumnItem(strings[i]);
        }

        String transactionName = strings[0].strip();
        String partnerName = strings[1].split("/")[0].strip();
        LocalDateTime transactionDateTime = LocalDateTime.parse(strings[2].strip(),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        transactionLineCounter.addTransaction(partnerName, new TransactionKey(transactionName, transactionDateTime));
    }
}
