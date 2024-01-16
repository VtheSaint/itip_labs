import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Warehouse {
    private final List<Integer> products;

    public Warehouse(List<Integer> products) {
        this.products = products;
    }

    public synchronized int takeProduct() {
        if (products.isEmpty()) {
            return 0;
        }
        return products.remove(0);
    }

    public static void main(String[] args) {
        List<Integer> products = new ArrayList<>();
        // Заполните список продуктами и их весами
        products.add(56);
        products.add(65);
        products.add(76);

        Warehouse warehouse = new Warehouse(products);

        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch finishSignal = new CountDownLatch(3);

        Thread worker1 = new Thread(new Worker("Грузчик 1", warehouse, startSignal, finishSignal));
        Thread worker2 = new Thread(new Worker("Грузчик 2", warehouse, startSignal, finishSignal));
        Thread worker3 = new Thread(new Worker("Грузчик 3", warehouse, startSignal, finishSignal));

        worker1.start();
        worker2.start();
        worker3.start();

        System.out.println("Начало переноса товаров.");
        startSignal.countDown(); // Начало переноса

        try {
            finishSignal.await(); // Ожидание завершения переноса
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Перенос товаров завершен.");
    }
}

class Worker implements Runnable {
    private final String name;
    private final Warehouse warehouse;
    private final CountDownLatch startSignal;
    private final CountDownLatch finishSignal;

    public Worker(String name, Warehouse warehouse, CountDownLatch startSignal, CountDownLatch finishSignal) {
        this.name = name;
        this.warehouse = warehouse;
        this.startSignal = startSignal;
        this.finishSignal = finishSignal;
    }

    @Override
    public void run() {
        try {
            startSignal.await(); // Ожидаем начала переноса товаров
            int totalWeight = 0;

            while (totalWeight < 150) {
                int productWeight = warehouse.takeProduct();
                if (productWeight == 0) {
                    break;
                }

                totalWeight += productWeight;
                System.out.println(name + " взял товар весом " + productWeight + " кг.");
            }

            System.out.println(name + " переносит товары общим весом " + totalWeight + " кг.");
            Thread.sleep(1000); // Имитируем разгрузку
            System.out.println(name + " закончил разгрузку.");
            finishSignal.countDown(); // Сообщаем, что завершили разгрузку
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}



