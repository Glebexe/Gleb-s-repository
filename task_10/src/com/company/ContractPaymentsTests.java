package com.company;

import org.junit.*;

public class ContractPaymentsTests extends Assert {

    @Test
    public void create_CreateListOfContracts_ContractsCountEqualsZero(){
        ContractPayments contractPayment = ContractPayments.create();
        assertEquals(0, contractPayment.getContractsCount());
    }

    @Test
    public void addContract_AddContractWithNumberAndDate_ContractsCountEqualsOne(){
        ContractPayments contractPayment = ContractPayments.create();
        contractPayment.addContract("1","20211218");
        assertEquals(1, contractPayment.getContractsCount());
    }
}
