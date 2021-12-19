package com.company;

import java.util.HashMap;

public class Contract {
    private String date;
    private HashMap<Integer, PaymentDocument> paymentDocuments;
    private int sumOfAllPayments;

    public Contract(String date){
        this.date = date;
        paymentDocuments = new HashMap<>();
        sumOfAllPayments = 0;
    }

    public void registerPaymentDocument(int sum, int number, PaymentDocumentType paymentType, String date){
        paymentDocuments.put(number,new PaymentDocument(sum,paymentType,date));
        sumOfAllPayments += sum;
    }

    public int getPaymentDocumentsCount() {
        return paymentDocuments.size();
    }

    public int getAmountOfPayments() {
        return sumOfAllPayments;
    }
}
