package com.company;

import org.junit.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void getAmount_CalculateContractAmountOfPayments_AmountEquals600(){
        ContractManager contractManager = ContractManager.create();
        contractManager.addContract("1","20211218");
        contractManager.registerPaymentDocument(100,1010,PaymentOrder,"1","20211219");
        contractManager.registerPaymentDocument(200,1011,BankOrder,"1","20211219");
        contractManager.registerPaymentDocument(300,1012,PaymentOrder,"1","20211219");
        assertEquals(600, contractManager.getContracts().get("1").getSumOfPayments());
    }

    @Test
    public void deletePayment_DeletePaymentWithContractNumberNumberAndDate_PaymentsCountEqualsTwo(){
        ContractManager contractManager = ContractManager.create();
        contractManager.addContract("1","20211218");
        contractManager.registerPaymentDocument(100,1010,PaymentOrder,"1","20211219");
        contractManager.registerPaymentDocument(200,1011,BankOrder,"1","20211219");
        contractManager.registerPaymentDocument(300,1012,PaymentOrder,"1","20211219");
        contractManager.deletePayment(1012,"1","20211219");
        assertEquals(2, contractManager.getContracts().get("1").getPaymentDocumentsCount());
    }

    @Test
    public void deletePayment_DeletePaymentWithContractNumberNumberAndDate_PaymentsCountEqualsZero(){
        ContractManager contractManager = ContractManager.create();
        contractManager.addContract("1","20211218");
        contractManager.registerPaymentDocument(100,1010,PaymentOrder,"1","20211219");
        contractManager.registerPaymentDocument(200,1011,BankOrder,"1","20211219");
        contractManager.registerPaymentDocument(300,1012,PaymentOrder,"1","20211219");
        contractManager.deletePayment(1012,"1","20211219");
        contractManager.deletePayment(1011,"1","20211219");
        contractManager.deletePayment(1010,"1","20211219");
        assertEquals(0, contractManager.getContracts().get("1").getPaymentDocumentsCount());
    }

    @Test
    public void getList_GetListOfAllPayments_ListEqualsTestList(){
        ContractManager contractManager = ContractManager.create();
        contractManager.addContract("1","20211218");
        contractManager.registerPaymentDocument(100,1010,PaymentOrder,"1","20211219");
        contractManager.registerPaymentDocument(200,1011,BankOrder,"1","20211219");
        contractManager.registerPaymentDocument(300,1012,PaymentOrder,"1","20211219");
        contractManager.addContract("2d","20211219");
        contractManager.registerPaymentDocument(111,111,PaymentOrder,"2d","20211219");
        contractManager.registerPaymentDocument(211,122,BankOrder,"2d","20211219");
        contractManager.registerPaymentDocument(311,133,PaymentOrder,"2d","20211219");

        List<Integer> payments = new ArrayList();
        payments.add(100);
        payments.add(200);
        payments.add(300);
        payments.add(311);
        payments.add(211);
        payments.add(111);

        assertArrayEquals(payments.toArray(),contractManager.getAllPayments().toArray());
    }

    @Test
    public void getPayments_GetAllPaymentsFromContract_ListEqualsTestList(){
        ContractManager contractManager = ContractManager.create();
        contractManager.addContract("1","20211218");
        contractManager.registerPaymentDocument(100,1010,PaymentOrder,"1","20211219");
        contractManager.registerPaymentDocument(200,1011,BankOrder,"1","20211219");
        contractManager.registerPaymentDocument(300,1012,PaymentOrder,"1","20211219");
        contractManager.addContract("2d","20211219");
        contractManager.registerPaymentDocument(111,111,PaymentOrder,"2d","20211219");
        contractManager.registerPaymentDocument(211,122,BankOrder,"2d","20211219");
        contractManager.registerPaymentDocument(311,133,PaymentOrder,"2d","20211219");

        List<Integer> payments = new ArrayList();
        payments.add(100);
        payments.add(200);
        payments.add(300);

        assertArrayEquals(payments.toArray(),contractManager.getContracts().get("1").getListOfPayments().toArray());
    }

    @Test
    public void getMap_GetMapOfContractsWithSumOfPayments_MapEqualsTestMap(){
        ContractManager contractManager = ContractManager.create();
        contractManager.addContract("1","20211218");
        contractManager.registerPaymentDocument(100,1010,PaymentOrder,"1","20211219");
        contractManager.registerPaymentDocument(200,1011,BankOrder,"1","20211219");
        contractManager.registerPaymentDocument(300,1012,PaymentOrder,"1","20211219");
        contractManager.addContract("2d","20211219");
        contractManager.registerPaymentDocument(111,111,PaymentOrder,"2d","20211219");
        contractManager.registerPaymentDocument(211,122,BankOrder,"2d","20211219");
        contractManager.registerPaymentDocument(311,133,PaymentOrder,"2d","20211219");

        List<String> contractsTest = new ArrayList();
        List<Integer> paymentsTest = new ArrayList();
        contractsTest.add("1");
        paymentsTest.add(600);
        contractsTest.add("2d");
        paymentsTest.add(633);

        HashMap<String,Integer> contractsWithPayments = contractManager.getAllContractsWithPayments();
        List<String> contracts = new ArrayList();
        List<Integer> payments = new ArrayList();

        for (Map.Entry<String, Integer> entry: contractsWithPayments.entrySet()){
            contracts.add(entry.getKey());
            payments.add(entry.getValue());
        }

        assertArrayEquals(contractsTest.toArray(), contracts.toArray());
        assertArrayEquals(paymentsTest.toArray(), payments.toArray());
    }

}
