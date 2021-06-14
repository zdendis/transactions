package cz.zdenekvlk.transactions.dto.processor;

import cz.zdenekvlk.transactions.dto.TransactionKey;

import java.util.*;

public class TransactionLineCounter implements LineCounter {
    private final Map<String, List<TransactionKey>> transactionLines;

    public TransactionLineCounter() {
        transactionLines = new HashMap<>();
    }

    public void addTransaction(String partner, TransactionKey transactionKey) {
        List<TransactionKey> keys = transactionLines.get(partner);
        if(keys == null) {
            transactionLines.put(partner, new ArrayList<>(Arrays.asList(transactionKey)));
        } else {
            keys.add(transactionKey);
        }
    }

    @Override
    public Integer getTransactionCount(String partner) {
        return transactionLines.get(partner).size();
    }

    @Override
    public List<TransactionKey> getTransactions(String partner) {
        return transactionLines.get(partner);
    }
}
