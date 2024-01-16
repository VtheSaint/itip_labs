public class ArrayAverage {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        // int[] arr = {};

        try {
        int average = calculateAverage(arr);
        System.out.println("Среднее арифметическое элементов массива: " + average);
        } catch (ArithmeticException e) {
            System.out.println("Ошибка: Деление на ноль. Массив пуст.");
        } catch (Exception e) {
            System.out.println("Произошла ошибка: " + e.getMessage());
        }
    }


    public static int calculateAverage(int[] arr) throws ArithmeticException {
        if (arr.length == 0) {
            throw new ArithmeticException("Массив пуст. Невозможно вычислить среднее.");
        }

        int total = 0;
        for (int num : arr) {
            total += num;
        }

        return total / arr.length;
    }
}