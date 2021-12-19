package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Contract {
    private String date;
    private HashMap<Integer, PaymentDocument> paymentDocuments;

    public Contract(String date){
        this.date = date;
        paymentDocuments = new HashMap<>();
    }

    public void registerPaymentDocument(int sum, int number, PaymentDocumentType paymentType, String date){
        paymentDocuments.put(number,new PaymentDocument(sum,paymentType,date));
    }

    public int getPaymentDocumentsCount() {
        return paymentDocuments.size();
    }

    public HashMap<Integer, PaymentDocument> getPaymentDocuments(){
        return paymentDocuments;
    }

    public int getSumOfPayments() {
        int sum = 0;
        for(PaymentDocument doc : paymentDocuments.values()){
            sum += doc.getSum();
        }
        return sum;
    }

    public List<Integer> getListOfPayments() {
        List<Integer> payments = new ArrayList();
        for(PaymentDocument paymentDoc : paymentDocuments.values()){
            payments.add(paymentDoc.getSum());
        }

        return payments;
    }
}
