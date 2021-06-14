package cz.zdenekvlk.transactions.dto.processor;

import com.opencsv.processor.RowProcessor;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@RequiredArgsConstructor
public class PartnerTransactionCounterProcessor implements RowProcessor {
    private final PartnerCounter partnerCounter;

    public boolean allowLine(String[] line) {
        String partnerName = line[1].split("/")[0].strip();
        partnerCounter.add(partnerName);
        line[line.length - 1] = String.valueOf(partnerCounter.get(partnerName));
        return true;
    }

    @Override
    public String processColumnItem(String column) {
        Objects.requireNonNull(column);
        return column.strip();
    }

    @Override
    public void processRow(String[] strings) {
        Arrays.stream(strings).forEach(this::processColumnItem);

        String partnerName = strings[1].split("/")[0].strip();
        partnerCounter.add(partnerName);
        strings[1] = strings[1] + "/" + partnerCounter.get(partnerName);
    }
}
