package com.company;

import java.util.HashMap;

public class ContractManager {

    private HashMap<Integer,Contract> contracts;

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
        contracts.put(Integer.parseInt(number),new Contract(date));
    }

    public HashMap<Integer,Contract> getContracts() {
        return contracts;
    }
}
