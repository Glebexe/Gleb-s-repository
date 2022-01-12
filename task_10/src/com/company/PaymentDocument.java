package com.company;

import java.util.HashMap;

public class PaymentDocument {
    HashMap<String,Integer> datedPayments;
    HashMap<PaymentDocumentType, HashMap<String,Integer>> typedPaymentDocuments;

    public PaymentDocument(){
        typedPaymentDocuments = new HashMap<>();
    }

    public PaymentDocument addPaymentDocument(int sum, PaymentDocumentType type, String date) {
        if(typedPaymentDocuments.containsKey(type)) {
            datedPayments = typedPaymentDocuments.get(type);
            datedPayments.put(date,sum);
            typedPaymentDocuments.put(type, datedPayments);
        }
        else{
            typedPaymentDocuments.put(type, new HashMap<>(){{put(date,sum);}});
        }

        return this;
    }

    public HashMap<PaymentDocumentType, HashMap<String, Integer>> getTypedPaymentDocuments() {
        return typedPaymentDocuments;
    }

    public int getSum(){
        int totalSum = 0;
        for(HashMap<String,Integer> datedDoc : typedPaymentDocuments.values()){
            for(Integer sum: datedDoc.values())
                totalSum += sum;
        }
        return totalSum;
    }
}
