package vmware;
import java.util.*;
import java.util.regex.*;

public class CheckIpv4Ipv6 {
    private static Pattern VALID_IPV4_PATTERN = null;
    private static Pattern VALID_IPV6_PATTERN = null;
    private static final String ipv4Pattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
    private static final String ipv6Pattern = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";

    public boolean isIpAddress(String ipAddress) {

        VALID_IPV4_PATTERN = Pattern.compile(ipv4Pattern, Pattern.CASE_INSENSITIVE);
        VALID_IPV6_PATTERN = Pattern.compile(ipv6Pattern, Pattern.CASE_INSENSITIVE);
        Matcher m1 = VALID_IPV4_PATTERN.matcher(ipAddress);
        if (m1.matches()) {
            System.out.println("Is ipv4");
            return true;
        }
        Matcher m2 = VALID_IPV6_PATTERN.matcher(ipAddress);
        return m2.matches();
    }

    public static void main(String[] args) {
        CheckIpv4Ipv6 ob = new CheckIpv4Ipv6();
        System.out.println(ob.isIpAddress("10.211.118.100"));
        System.out.println(ob.isIpAddress("2001:0db8:85a3:0000:0000:8a2e:0370:7334"));

    }

}
