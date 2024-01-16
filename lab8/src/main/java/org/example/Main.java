package org.example;


public class Main {
    public static void main(String[] args) {
        DataManager dataManager = new DataManager();
        dataManager.loadData("/Users/velimirhlebnikov/МТУСИ/ИТ/labs/lab8/src/main/java/org/example/InputData.json");

        Processors.SumOfSales p1 = new Processors.SumOfSales();
        dataManager.registerDataProcessor(p1);

        Processors.SalesByDay p2 = new Processors.SalesByDay("30/01/2023");
        dataManager.registerDataProcessor(p2);

        Processors.AddSale p3 = new Processors.AddSale(new DataManager.Product());
        dataManager.registerDataProcessor(p3);

        dataManager.processData();
        dataManager.saveData("/Users/velimirhlebnikov/МТУСИ/ИТ/labs/lab8/src/main/java/org/example/OutputData.json");

    }

}