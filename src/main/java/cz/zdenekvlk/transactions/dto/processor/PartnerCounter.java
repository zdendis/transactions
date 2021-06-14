package cz.zdenekvlk.transactions.dto.processor;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PartnerCounter {
    private final Map<String, Integer> partnerCounts = new ConcurrentHashMap<>();

    public void add(String partner) {
        Integer count = partnerCounts.get(partner);
        partnerCounts.put(partner, count != null ? ++count : 1);
    }

    public Integer get(String partner) {
        return partnerCounts.get(partner);
    }
}
