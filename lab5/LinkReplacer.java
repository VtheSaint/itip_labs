public class LinkReplacer {
    public static void main(String[] args) {
        String text = "Visit my website at http://www.example.com. You can also find me on LinkedIn at https://www.linkedin.com/in/user.";
        String replacedText = text.replaceAll("https?://\\S+", "<a href=\"$0\">$0</a>");
        System.out.println(replacedText);
    }
}

