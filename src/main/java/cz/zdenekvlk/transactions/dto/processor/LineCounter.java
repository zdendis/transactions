package cz.zdenekvlk.transactions.dto.processor;

import cz.zdenekvlk.transactions.dto.TransactionKey;

import java.util.List;

public interface LineCounter {
    Integer getTransactionCount(String partner);
    List<TransactionKey> getTransactions(String partner);
    void reset();
}
