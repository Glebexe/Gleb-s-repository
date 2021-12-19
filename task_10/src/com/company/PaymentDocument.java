package com.company;

public class PaymentDocument {
    private int sum;
    private PaymentDocumentType type;
    private String date;

    public PaymentDocument(int sum, PaymentDocumentType type, String date) {
        this.sum = sum;
        this.type = type;
        this.date = date;
    }

    public int getSum(){
        return sum;
    }
}
