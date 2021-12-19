package com.company;

import org.junit.*;

import static com.company.PaymentDocumentType.*;

public class ContractPaymentsTests extends Assert {

    @Test
    public void create_CreateListOfContracts_ContractsCountEqualsZero(){
        ContractManager contractManager = ContractManager.create();
        assertEquals(0, contractManager.getContractsCount());
    }

    @Test
    public void addContract_AddContractWithNumberAndDate_ContractsCountEqualsOne(){
        ContractManager contractManager = ContractManager.create();
        contractManager.addContract("1","20211218");
        assertEquals(1, contractManager.getContractsCount());
    }

    @Test
    public void addContract_AddContractsWithNumberAndDate_ContractsCountEqualsThree(){
        ContractManager contractManager = ContractManager.create();
        contractManager.addContract("1","20211218");
        contractManager.addContract("101","20211218");
        contractManager.addContract("111","20211218");
        assertEquals(3, contractManager.getContractsCount());
    }

    @Test
    public void addContract_AddContractsWithNumberAndDate_PaymentDocumentCountEqualsZero(){
        ContractManager contractManager = ContractManager.create();
        contractManager.addContract("1","20211218");
        assertEquals(0, contractManager.getContracts().get("1").getPaymentDocumentsCount());
    }

    @Test
    public void registerPaymentDocument_RegisterPaymentDocumentWithData_DocumentsCountEqualsOne(){
        ContractManager contractManager = ContractManager.create();
        contractManager.addContract("1","20211218");
        contractManager.registerPaymentDocument(100,1010,PaymentOrder,"1","20211219");
        assertEquals(1, contractManager.getContracts().get("1").getPaymentDocumentsCount());
    }

    @Test
    public void registerPaymentDocument_RegisterPaymentDocumentWithData_DocumentsCountEqualsThree(){
        ContractManager contractManager = ContractManager.create();
        contractManager.addContract("1","20211218");
        contractManager.registerPaymentDocument(100,1010,PaymentOrder,"1","20211219");
        contractManager.registerPaymentDocument(200,1011,BankOrder,"1","20211219");
        contractManager.registerPaymentDocument(300,1012,PaymentOrder,"1","20211219");
        assertEquals(3, contractManager.getContracts().get("1").getPaymentDocumentsCount());
    }
}
