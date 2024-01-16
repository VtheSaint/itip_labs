import java.util.regex.*;
public class NumberFinder {
    public static void main(String[] args) {
        String text = "The 19.56 price of the product is $19.99.00/9?8j8";
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
