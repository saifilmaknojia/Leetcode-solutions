import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution_IP {
    Pattern p = Pattern.compile("[0-9a-fA-F]+");

    public String validIPAddress(String IP) {
        // check IPV4
        // check IPV6
        // extra leading zero invalid, > 4 leading zeroes
        int ip_len = IP.length();
        if (ip_len == 0)
            return "Neither";

        if (IP.charAt(ip_len - 1) == ':' || IP.charAt(ip_len - 1) == '.')
            return "Neither";

        String[] arr = null;
        if (IP.indexOf(':') >= 0)
            arr = IP.split(":");
        else
            arr = IP.split("\\.");

        int len = arr.length;
        // System.out.println(len);

        if (len == 4)
            return checkIPv4(arr) ? "IPv4" : "Neither";
        else if (len == 8)
            return checkIPv6(arr) ? "IPv6" : "Neither";
        else
            return "Neither";
    }

    private boolean checkIPv4(String[] addr) {
        for (String point : addr) {
            int len = point.length();
            if (len == 0)
                return false;
            char at_0 = point.charAt(0);
            if (len == 1 && at_0 == '0')
                continue;
            else if (at_0 == '0' || at_0 == '-')
                return false;
            else {
                try {
                    int num = Integer.parseInt(point);
                    if (num > 255 || num < 0)
                        return false;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkIPv6(String[] address) {
        int leading_zeroes = 0;
        Matcher m = p.matcher("");
        for (String addr : address) {
            // it means two : are together
            if (addr.length() == 0 || addr.length() > 4)
                return false;
            else if (addr.charAt(0) == '0')
                leading_zeroes++;

            if (leading_zeroes > 4)
                return false;
            else {
                // Matcher m = p.matcher(addr);
                m.reset(addr);
                if (!m.matches())
                    return false;
            }
        }

        return true;
    }
}

public class Validate_IP_Address_68 {

}