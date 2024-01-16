package org.example;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Processors {
    public static abstract class Processor<T, R> {
        @DataProcessor
        public abstract CompletableFuture<R> process(T dataItem);
    }


    public static class SalesByDay extends Processor<List<DataManager.Product>, List<DataManager.Product>> {
        String date = "";

        public SalesByDay(String date) {
            super();
            this.date = date;
        }

        @Override
        @DataProcessor(status = "Counting sales by the day...")
        public CompletableFuture<List<DataManager.Product>> process(List<DataManager.Product> dataItem) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Method m = this.getClass().getMethod("process", List.class);
                    System.out.println(m.getAnnotation(DataProcessor.class).status());
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                return dataItem.stream().filter( item -> item.sold_at.equals(date)).collect(Collectors.toList());
            });
        }
    }

    public static class SumOfSales extends Processor<List<DataManager.Product>, Integer> {
        @Override
        @DataProcessor(status = "Counting sum of sales...")
        public CompletableFuture<Integer> process(List<DataManager.Product> dataItem) {
            return CompletableFuture.supplyAsync(() -> {
                try {
                    Method m = this.getClass().getMethod("process", List.class);
                    System.out.println(m.getAnnotation(DataProcessor.class).status());
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                return dataItem.stream().map(item -> item.count * item.sold_price).reduce(0, Integer::sum);
            });
        }
    }

    public static class AddSale extends Processor<List<DataManager.Product>, List<DataManager.Product>> {
        DataManager.Product to_add = null;

        public AddSale(DataManager.Product to_add) {
            this.to_add = to_add;
        }
        @Override
        @DataProcessor(status = "Adding new sale...")
        public CompletableFuture<List<DataManager.Product>> process(List<DataManager.Product> dataItem) {
            return CompletableFuture.supplyAsync(() -> {
                if (to_add != null) {
                    dataItem.add(to_add);
                }
                try {
                    Method m = this.getClass().getMethod("process", List.class);
                    System.out.println(m.getAnnotation(DataProcessor.class).status());
                } catch (NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
                return dataItem;
            });
        }
    }

}