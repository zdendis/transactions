package cz.zdenekvlk.transactions.service;

import org.springframework.stereotype.Service;

@Service
public class Solution implements SolutionInterface {

    @Override
    public String solution(String location) {
        System.out.println(location);
        return location;
    }


//    private TransactionFile readFile(String location) {
//
//    }
}
