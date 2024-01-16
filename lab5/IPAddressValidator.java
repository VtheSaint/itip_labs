import java.util.regex.*;
public class IPAddressValidator {
    public static void main(String[] args) {
        String ipAddress = "192.168.1.1";
        Pattern pattern = Pattern.compile("^(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\.(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)$");
        Matcher matcher = pattern.matcher(ipAddress);
        if (matcher.matches()) {
            System.out.println("IP-адрес корректен.");
        } else {
            System.out.println("IP-адрес некорректен.");
        }
    }
}
