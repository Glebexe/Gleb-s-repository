package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void registerPaymentDocument(int sum, int paymentDocumentNumber, PaymentDocumentType paymentType,
                                        String contractNumber, String date) {
        contracts.get(contractNumber).registerPaymentDocument(sum,paymentDocumentNumber,paymentType,date);
    }

    public List<Integer> getAllPayments() {
        List<Integer> payments = new ArrayList();
        for(Contract contract : contracts.values()){
            for(PaymentDocument paymentDoc : contract.getPaymentDocuments().values())
            payments.add(paymentDoc.getSum());
        }

        return payments;
    }

    public void deletePayment(int paymentDocumentNum, String contractNum, String paymentDate) {
        contracts.get(contractNum).getPaymentDocuments().remove(paymentDocumentNum);
    }

    public HashMap<String, Integer> getAllContractsWithPayments() {
        HashMap<String,Integer> contractsWithPayments = new HashMap();
        for (Map.Entry<String,Contract> entry: contracts.entrySet()){
            contractsWithPayments.put(entry.getKey(), entry.getValue().getSumOfPayments());
        }

        return contractsWithPayments;
    }
}
