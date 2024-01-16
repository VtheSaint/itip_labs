public class task2 {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i ++) {
            String s = args[i];
            if (isPalindrome(s.split(""))) {
                System.out.print(s);
                System.out.println(" is Palindrome!");
            } else {
                System.out.print(s);
                System.out.println(" is not a  Palindrome");
            }

            
        }
    }

    public static String[] reverse(String[] s) {
        int i = 0;
        int number = s.length % 2 == 0 ? s.length / 2 : (s.length - 1) / 2;
        
        while (i < number) {
            String box = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = box;
            i += 1;
        }
        return s;
    }


    public static boolean isPalindrome(String[] s) {
        String[] s2 = s.clone();
        s = reverse(s);

        for (int i = 0; i < s2.length; i++) {
            if (!s2[i].equals(s[i])) {return false;}
        }
        return true;
    }
}
