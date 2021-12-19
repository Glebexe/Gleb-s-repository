package com.company;

import java.util.HashMap;

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

    public int getAmountOfPayments() {
        int sum = 0;
        for(PaymentDocument doc : paymentDocuments.values()){
            sum += doc.getSum();
        }
        return sum;
    }
}
