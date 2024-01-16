import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class MatrixMaxElementMultiThreaded {
    public static int findMaxElement(int[][] matrix, int numThreads) throws InterruptedException, ExecutionException {
        // Создаем ExecutorService с указанным количеством потоков
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        int rows = matrix.length;
        int cols = matrix[0].length;
        int chunkSize = rows / numThreads;
        int startIndex = 0;
        int endIndex = chunkSize;
        int maxElement = Integer.MIN_VALUE;

        // Создаем массив объектов Future для хранения результатов каждой части
        Future<Integer>[] results = new Future[numThreads];

        // Разделяем матрицу на части и запускаем поиск максимального элемента в каждом потоке
        for (int i = 0; i < numThreads; i++) {
            final int startRow = startIndex;
            final int endRow = endIndex;
            results[i] = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() {
                    int localMax = Integer.MIN_VALUE;
                    for (int row = startRow; row < endRow; row++) {
                        for (int col = 0; col < cols; col++) {
                            if (matrix[row][col] > localMax) {
                                localMax = matrix[row][col];
                            }
                        }
                    }
                    return localMax;
                }
            });
            startIndex = endIndex;
            endIndex = Math.min(endIndex + chunkSize, rows);
        }

        // Дожидаемся завершения всех потоков и находим максимальный элемент
        for (Future<Integer> result : results) {
            int localMax = result.get();
            if (localMax > maxElement) {
                maxElement = localMax;
            }
        }

        // Завершаем ExecutorService
        executorService.shutdown();

        return maxElement;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int numThreads = 3; // Количество потоков

        int maxElement = findMaxElement(matrix, numThreads);
        System.out.println("Наибольший элемент в матрице: " + maxElement);
    }
}
