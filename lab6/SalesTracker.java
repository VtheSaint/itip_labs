import java.util.concurrent.ConcurrentHashMap;

public class SalesTracker {
    private ConcurrentHashMap<String, Integer> sales;

    public SalesTracker() {
        sales = new ConcurrentHashMap<>();
    }

    public void addSale(String product, int quantitySold) {
        // Обновляем количество продаж для товара
        sales.merge(product, quantitySold, Integer::sum);
    }

    public void printSalesReport() {
        System.out.println("Список проданных товаров:");
        for (ConcurrentHashMap.Entry<String, Integer> entry : sales.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public int getTotalSalesAmount() {
        int totalSales = 0;
        for (int salesAmount : sales.values()) {
            totalSales += salesAmount;
        }
        return totalSales;
    }

    public String getMostPopularProduct() {
        ConcurrentHashMap.Entry<String, Integer> maxEntry = null;
        for (ConcurrentHashMap.Entry<String, Integer> entry : sales.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        return (maxEntry != null) ? maxEntry.getKey() : "Нет данных о продажах";
    }

    public static void main(String[] args) {
        SalesTracker tracker = new SalesTracker();

        // Добавление проданных товаров
        tracker.addSale("ProductA", 10);
        tracker.addSale("ProductB", 15);
        tracker.addSale("ProductA", 5);
        tracker.addSale("ProductC", 8);

        // Вывод отчета о продажах
        tracker.printSalesReport();

        // Получение общей суммы продаж
        System.out.println("Общая сумма продаж: " + tracker.getTotalSalesAmount());

        // Получение наиболее популярного товара
        System.out.println("Наиболее популярный товар: " + tracker.getMostPopularProduct());
    }
}
