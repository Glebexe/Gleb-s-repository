package com.company;

import java.util.*;

import static com.company.PaymentDocumentType.BankOrder;
import static com.company.PaymentDocumentType.PaymentOrder;

public class ConsoleInteraction {
    Scanner in;
    ContractManager contractManager;

    public ConsoleInteraction(){
        in = new Scanner(System.in);
        contractManager = ContractManager.create();
    }

    public void printInterface(){
        availableCommands();
        while(true)
            commandsProcessing();
    }

    private void commandsProcessing() {
        System.out.println("Введите номер команды:");
        String inputCommand = in.nextLine();
        System.out.println("Введите параметры:");
        String[] inputParameters = in.nextLine().split(" ");
        try {
            switch (inputCommand) {
                case "1"-> addContract(inputParameters);
                case "2"-> registerPaymentDocument(inputParameters);
                case "3"-> getAllPaymentsFromContract(inputParameters);
                case "4"-> getSumOfPaymentsFromContract(inputParameters);
                case "5"-> deletePayment(inputParameters);
                case "6"-> getAllContractsWithPayments();
                case "7"-> getAllPayments();
                default -> System.out.println("Команды с таким номером не существует!");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAllPayments() {
        for(Map.Entry<String,Contract> contract : contractManager.getContracts().entrySet()){
            for(Map.Entry<Integer,PaymentDocument> paymentDoc : contract.getValue().getPaymentDocuments().entrySet())
                for(Map.Entry<PaymentDocumentType,HashMap<String,Integer>> typedDoc : paymentDoc.getValue().getTypedPaymentDocuments().entrySet())
                    for(Map.Entry<String,Integer> datedPayment : typedDoc.getValue().entrySet())
                    System.out.println("Договор: " + contract.getKey() + " Номер платёжного документа: " + paymentDoc.getKey()
                    + " Тип: " + typedDoc.getKey() + " Дата: " + datedPayment.getKey() + " Сумма: " + datedPayment.getValue());
        }
    }

    private void getAllContractsWithPayments() {
        HashMap<String,Integer> contractsWithPayments = contractManager.getAllContractsWithPayments();
        for(Map.Entry<String,Integer> contractWithPayment: contractsWithPayments.entrySet()){
            System.out.println("Договор: " + contractWithPayment.getKey() + " Общая сумма: " + contractWithPayment.getValue());
        }
    }

    private void deletePayment(String[] inputParameters) throws Exception {
        if(inputParameters.length == 5 && new Scanner(inputParameters[0]).hasNextInt() && (isCommandOfPaymentTypeCorrect(inputParameters, 1, 2))
                && isADate(inputParameters[4])){
            if(doesPaymentDocumentExist(inputParameters)){
                contractManager.deletePayment(Integer.parseInt(inputParameters[0]),
                        paymentDocumentTypeCommandProcessing(inputParameters[1]+inputParameters[2]),
                        inputParameters[3],inputParameters[4]);
            } else
                throw new Exception("Такого платежа не существует!");
        } else
            throw new Exception("Неверно введены параметры!");
    }

    private void getSumOfPaymentsFromContract(String[] inputParameters) throws Exception {
        if(inputParameters.length == 1){
            if(contractManager.getContracts().containsKey(inputParameters[0])){
                System.out.println("Договор: " + inputParameters[0] + " Общая сумма платежей: " +
                        contractManager.getContracts().get(inputParameters[0]).getSumOfPayments());
            } else
                throw new Exception("Такого договора не существует!");
        } else
            throw new Exception("Неверно введены параметры!");
    }


    private void addContract(String[] inputParameters) throws Exception {
        if (inputParameters.length == 2 && isADate(inputParameters[1])) {
            if(!doesContractExist(inputParameters[0]))
                contractManager.addContract(inputParameters[0], inputParameters[1]);
            else
                throw new Exception("Договор с таким номером уже существует!");
        } else
            throw new Exception("Неверно введены параметры!");
    }

    private void registerPaymentDocument(String[] inputParameters) throws Exception {
        if(areParametersForRegisterPaymentDocumentCorrect(inputParameters)){
                if(isSumPositiveNumber(inputParameters[1])){
                    if(!doesPaymentDocumentExist(Arrays.copyOfRange(inputParameters, 1, inputParameters.length))){
                        contractManager.registerPaymentDocument(Integer.parseInt(inputParameters[0]),
                                Integer.parseInt(inputParameters[1]),paymentDocumentTypeCommandProcessing(inputParameters[2]+inputParameters[3]),
                                inputParameters[4],inputParameters[5]);
                    }
                    else
                        throw new Exception("Платеж с таким номером договора, номером документа," +
                                " типом документа и датой уже существует!");
                } else
                    throw new Exception("Сумма должна быть положительным чисолом!");
        } else
            throw new Exception("Неверно введены параметры!");
    }

    private boolean areParametersForRegisterPaymentDocumentCorrect(String[] inputParameters) {
        return inputParameters.length == 6 && new Scanner(inputParameters[0]).hasNextInt() &&
                new Scanner(inputParameters[1]).hasNextInt() && isCommandOfPaymentTypeCorrect(inputParameters, 2, 3) && isADate(inputParameters[5]);
    }

    private boolean isCommandOfPaymentTypeCorrect(String[] inputParameters, int i, int i2) {
        return (inputParameters[i] + inputParameters[i2]).equals("Платежноепоручение")
                || (inputParameters[i] + inputParameters[i2]).equals("Банковскийордер");
    }

    private boolean isSumPositiveNumber(String inputParameter) {
        return Integer.parseInt(inputParameter) >= 0;
    }
    private boolean doesPaymentDocumentExist(String[] inputParameters) {
        return doesContractExist(inputParameters[3])
                && doesPaymentExist(inputParameters)
                && doesPaymentWithTypeExist(inputParameters)
                && doesPaymentWithTypeAndDateExist(inputParameters);
    }

    private boolean doesContractExist(String contractNum){
        return contractManager.getContracts().containsKey(contractNum);
    }
    private boolean doesPaymentExist(String[] inputParameters) {
        return contractManager.getContracts().get(inputParameters[3]).getPaymentDocuments().containsKey(
                Integer.parseInt(inputParameters[0]));
    }
    private boolean doesPaymentWithTypeExist(String[] inputParameters) {
        return contractManager.getContracts().get(inputParameters[3])
                .getPaymentDocuments().get(Integer.parseInt(inputParameters[0])).getTypedPaymentDocuments()
                .containsKey(paymentDocumentTypeCommandProcessing(inputParameters[1]+inputParameters[2]));
    }
    private boolean doesPaymentWithTypeAndDateExist(String[] inputParameters) {
        return contractManager
        .getContracts().get(inputParameters[3]).getPaymentDocuments().get(Integer.parseInt(inputParameters[0]))
        .getTypedPaymentDocuments().get(paymentDocumentTypeCommandProcessing(inputParameters[1]+inputParameters[2])).containsKey(inputParameters[4]);
    }

    private PaymentDocumentType paymentDocumentTypeCommandProcessing(String command){
        if(command.equals("Банковскийордер"))
            return BankOrder;
        else
            return PaymentOrder;
    }

    private void getAllPaymentsFromContract(String[] inputParameters) throws Exception {
        if(inputParameters.length == 1){
            if(contractManager.getContracts().containsKey(inputParameters[0])){
                System.out.println("Контракт: " + inputParameters[0]);
                printAllPayments(inputParameters[0]);
            } else
                throw new Exception("Такого договора не существует!");
        } else
            throw new Exception("Неверно введены параметры!");
    }

    private void printAllPayments(String inputParameter) {
        for (Map.Entry<Integer, PaymentDocument> paymentDocument: contractManager.getContracts().get(inputParameter).getPaymentDocuments().entrySet()){
            for (Map.Entry<PaymentDocumentType, HashMap<String,Integer>> typedPaymentDocument: paymentDocument.getValue().getTypedPaymentDocuments().entrySet())
                for (Map.Entry<String,Integer> datedPayments: typedPaymentDocument.getValue().entrySet())
                    System.out.println("Номер: " + paymentDocument.getKey() + " Тип: " + typedPaymentDocument.getKey() +
                            " Дата: " + datedPayments.getKey() + " Сумма:" + datedPayments.getValue());
        }
    }

    private boolean isADate(String inputParameter) {
        return inputParameter.length() == 8 && new Scanner(inputParameter).hasNextInt();
    }


    private void availableCommands() {
        System.out.println("Доступные команды:");
        System.out.println("1. Добавить договор. Параметры: {номер договора} {дата}");
        System.out.println("2. Зарегестрировать платёжный документ. Параметры: {сумма} {номер платёжного документа} {тип(два слова)} {номер договора} {дата}");
        System.out.println("3. Найти все платежи по договору. Параметры: {номер договора}");
        System.out.println("4. Получить сумму всех платежей по договору. Параметры: {номер договора}");
        System.out.println("5. Удалить платёж. Параметры: {номер платёжного документа} {тип(два слова)} {номер договора} {дата}");
        System.out.println("6. Получить список всех договоров с их суммарными суммами платежей. Параметры: {нет}");
        System.out.println("7. Получить список всех платежей. Параметры: {нет}");
        System.out.println("Чтобы выбрать команду, введите её номер.");
    }
}
