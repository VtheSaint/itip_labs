import java.util.ArrayList;

public class task1 {
    public static void simple() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 1; i < 100; i ++) {
            if (isPrime(i) == true) {
                result.add(i);
            }
        }
        System.out.println(result);
    }

    public static boolean isPrime(int i) {
        int d = 2;
        while (d*d < i) {
            if ( i % d == 0 ) {
                return false;
            }
            d += 1;
        }
        if (d*d == i) { return false; }
        if (i % 10 != 3) { return false;}
        return true;
    }

    public static void main(String[] args) {
        simple();
    }

}