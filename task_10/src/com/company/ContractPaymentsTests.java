package com.company;

import org.junit.*;

public class ContractPaymentsTests extends Assert {

    @Test
    public void create_CreateListOfContracts_ContractsCountEqualsZero(){
        ContractManager contractPayment = ContractManager.create();
        assertEquals(0, contractPayment.getContractsCount());
    }

    @Test
    public void addContract_AddContractWithNumberAndDate_ContractsCountEqualsOne(){
        ContractManager contractPayment = ContractManager.create();
        contractPayment.addContract("1","20211218");
        assertEquals(1, contractPayment.getContractsCount());
    }

    @Test
    public void addContract_AddContractsWithNumberAndDate_ContractsCountEqualsThree(){
        ContractManager contractPayment = ContractManager.create();
        contractPayment.addContract("1","20211218");
        contractPayment.addContract("101","20211218");
        contractPayment.addContract("111","20211218");
        assertEquals(3, contractPayment.getContractsCount());
    }

    @Test
    public void addContract_AddContractsWithNumberAndDate_PaymentDocumentCountEqualsZero(){
        ContractManager contractPayment = ContractManager.create();
        contractPayment.addContract("1","20211218");
        assertEquals(0, contractPayment.getContracts().get(1).getPaymentDocumentsCount());
    }

    /*@Test
    public void registerPaymentDocument_RegisterPaymentDocument_DocumentsCountEqualsOne(){
        ContractManager contractPayment = ContractManager.create();
        contractPayment.addContract("1","20211218");
        contractPayment.addContract("101","20211218");
        contractPayment.addContract("111","20211218");
        assertEquals(3, contractPayment.getContractsCount());
    }*/
}
