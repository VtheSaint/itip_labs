import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class ArraySumMultiThreaded {
    public static int parallelSum(int[] arr, int numThreads) throws InterruptedException, ExecutionException {
        // Создаем ExecutorService с указанным количеством потоков
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        int chunkSize = arr.length / numThreads;
        int startIndex = 0;
        int endIndex = chunkSize;
        int sum = 0;

        // Создаем массив объектов Future для хранения результатов каждой части
        Future<Integer>[] results = new Future[numThreads];

        // Разделяем массив на части и запускаем вычисления в каждом потоке
        for (int i = 0; i < numThreads; i++) {
            final int start = startIndex;
            final int end = endIndex;
            results[i] = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() {
                    int localSum = 0;
                    for (int j = start; j < end; j++) {
                        localSum += arr[j];
                    }
                    return localSum;
                }
            });
            startIndex = endIndex;
            endIndex = Math.min(endIndex + chunkSize, arr.length);
        }

        // Дожидаемся завершения всех потоков и суммируем результаты
        for (Future<Integer> result : results) {
            sum += result.get();
        }

        // Завершаем ExecutorService
        executorService.shutdown();

        return sum;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int numThreads = 4; // Количество потоков

        int sum = parallelSum(array, numThreads);
        System.out.println("Сумма элементов массива: " + sum);
    }
}
