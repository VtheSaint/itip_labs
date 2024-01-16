package org.example;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


public class DataManager {
    private final List<Processors.Processor> dataProcessors = new ArrayList<>();
    private List<Product> data = new ArrayList<>();
    private final List<Object> result = new ArrayList<>();
    private final List<CompletableFuture<Object>> futures = new ArrayList<>();

    public void registerDataProcessor(Processors.Processor processor) {
        dataProcessors.add(processor);
    }

    public void loadData(String source) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            data = mapper.readValue(new File(source),  new TypeReference<>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processData() {
        for (Processors.Processor processor : dataProcessors) {
            CompletableFuture<Object> future = processor.process(data);
            futures.add(future);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[futures.size()])
        );

        try {
            allOf.get(); // Блокируем выполнение до завершения всех задач
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        // Собираем результаты выполнения всех задач
        for (CompletableFuture<Object> future : futures) {
            try {
                result.add(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    public void saveData(String destination) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        try {
            mapper.writeValue(new File(destination), result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Product {
        public String sold_at;
        public String name;
        public int count;
        public int sold_price;
        public String manufacturer_country;


        public Product() {
            this.name = "NEW PRODUCT";
            this.sold_at = "12.02.2015";
            this.sold_price = 1;
            this.count = 1;
        }
    }

}
