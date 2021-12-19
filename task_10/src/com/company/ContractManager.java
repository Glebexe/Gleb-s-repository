package com.company;

import java.util.HashMap;

public class ContractManager {

    private HashMap<String,Contract> contracts;

    private ContractManager(){
        contracts = new HashMap<>();
    }

    public static ContractManager create() {
        return new ContractManager();
    }

    public int getContractsCount() {
        return contracts.size();
    }

    public void addContract(String number, String date) {
        contracts.put(number,new Contract(date));
    }

    public HashMap<String,Contract> getContracts() {
        return contracts;
    }
}
