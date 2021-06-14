package cz.zdenekvlk.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TransactionKey implements Comparable<TransactionKey>{
    private String transactionName;
    private LocalDateTime transactionDateTime;

    @Override
    public int compareTo(TransactionKey transactionKey) {
        return transactionDateTime.compareTo(transactionKey.getTransactionDateTime());
    }
}
