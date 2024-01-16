import java.util.regex.*;
public class WordFinder {
    public static void main(String[] args) {
        String text = "The quick brown fox jumps over the lazy dog";
        char targetLetter = 't';
        Pattern pattern = Pattern.compile("\\b" + targetLetter + "\\w*\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group());
        }
    }
}
