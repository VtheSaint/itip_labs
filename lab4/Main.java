class CustomUnsupportedOperationException extends UnsupportedOperationException {
    public CustomUnsupportedOperationException(String message) {
        super(message);
    }
}

class Calculator {
    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int divide(int a, int b) {
        if (b == 0) {
            throw new CustomUnsupportedOperationException("Division by zero is not allowed.");
        }
        return a / b;
    }
}

public class Main {
    public static void main(String[] args) {
        try {
            int result = Calculator.add(5, 3);
            System.out.println("5 + 3 = " + result);

            result = Calculator.subtract(8, 2);
            System.out.println("8 - 2 = " + result);

            result = Calculator.multiply(4, 7);
            System.out.println("4 * 7 = " + result);

            result = Calculator.divide(10, 2);
            System.out.println("10 / 2 = " + result);

            // Попытка выполнить операцию, которая не поддерживается
            result = Calculator.divide(5, 0); // Деление на ноль вызовет IllegalArgumentException
            System.out.println("5 / 0 = " + result);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (CustomUnsupportedOperationException e) {
            System.err.println("Unsupported operation: " + e.getMessage());
        }
    }
}

