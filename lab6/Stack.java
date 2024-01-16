import java.util.EmptyStackException;

public class Stack<T> {
    private static final int DEFAULT_CAPACITY = 3;
    private Object[] elements;
    private int size;

    public Stack() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void push(T item) {
        ensureCapacity();
        elements[size++] = item;
    }

    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T item = peek();
        elements[--size] = null; // Освобождаем ссылку на удаленный элемент
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        @SuppressWarnings("unchecked")
        T item = (T) elements[size - 1];
        return item;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == elements.length) {
            int newCapacity = elements.length * 2;
            Object[] newElements = new Object[newCapacity];
            System.arraycopy(elements, 0, newElements, 0, size);
            System.out.println(newCapacity);
            elements = newElements;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("Size of stack: " + stack.size());
        stack.push(4);

        System.out.println("Size of stack: " + stack.size());
        System.out.println("Top element: " + stack.peek());

        System.out.println("Popping elements:");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}

