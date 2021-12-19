package com.company;

import java.util.HashMap;

public class Contract {
    private String date;
    private HashMap<Integer, PaymentDocument> paymentDocuments;

    public Contract(String date){
        this.date = date;
        paymentDocuments = new HashMap<>();
    }

}
