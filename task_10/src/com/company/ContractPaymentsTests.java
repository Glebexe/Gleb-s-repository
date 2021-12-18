package com.company;

import org.junit.*;

public class ContractPaymentsTests extends Assert {

    @Test
    public void create_CreateListOfContracts_ContractsCountEqualsZero(){
        ContractPayments contractPayment = ContractPayments.create();
        assertEquals(0, contractPayment.getContractsCount());
    }
}
