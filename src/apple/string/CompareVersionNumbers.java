package apple.string;

public class CompareVersionNumbers {
    public int compareVersion(String version1, String version2) {
        if (version1 == null || version1.length() == 0 || version2 == null || version2.length() == 0) return 0;
        String[] parts1 = version1.split("\\.");
        String[] parts2 = version2.split("\\.");
        int i = 0;
        for (; i < Math.min(parts1.length, parts2.length); i ++) {
            String str1 = parts1[i];
            String str2 = parts2[i];
            Integer num1 = Integer.parseInt(str1);
            Integer num2 = Integer.parseInt(str2);
            if (num1.intValue() > num2.intValue()) {
                return 1;
            }
            else if (num1.intValue() < num2.intValue()) {
                return -1;
            }
        }
        if (i < parts1.length) {
            // important !!!
            // check if is all zeros
            while(i < parts1.length) {
                if (Integer.parseInt(parts1[i]) > 0) {
                    return 1;
                }
                i ++;
            }
        }
        else if (i < parts2.length) {
            // check if is all zeros
            while(i < parts2.length) {
                if (Integer.parseInt(parts2[i]) > 0) {
                    return -1;
                }
                i ++;
            }
        }
        return 0;
    }
}
