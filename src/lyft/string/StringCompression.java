package lyft.string;

public class StringCompression {
    public int compress(char[] chars) {
        int prev = 0;
        char prech = chars[0];
        int count = 1;
        for (int i = 1; i < chars.length; i ++) {
            char ch = chars[i];
            if (prech == ch) {
                count ++;
            }
            else {
                String countStr = count + "";
                chars[prev] = prech;
                prev ++;
                if (count > 1) {
                    for (char chs: countStr.toCharArray()) {
                        chars[prev] = chs;
                        prev ++;
                    }
                }
                count = 1;
                prech = ch;
            }
        }
        String countStr = count + "";
        chars[prev] = prech;
        prev ++;
        if (count > 1) {
            for (char chs: countStr.toCharArray()) {
                chars[prev] = chs;
                prev ++;
            }
        }
        return prev;
    }
    //Input: "RRRBBBCQQR";
    //Output: "R3B3C1Q2R1"

    public int compress_variance(char[] chars) {
        int prev = 0;
        char prech = chars[0];
        int count = 1;
        for (int i = 1; i < chars.length; i ++) {
            char ch = chars[i];
            if (prech == ch) {
                count ++;
            }
            else {
                String countStr = count + "";
                chars[prev] = prech;
                prev ++;
                for (char chs: countStr.toCharArray()) {
                    chars[prev] = chs;
                    prev ++;
                }
                count = 1;
                prech = ch;
            }
        }
        String countStr = count + "";
        chars[prev] = prech;
        prev ++;
        for (char chs: countStr.toCharArray()) {
            chars[prev] = chs;
            prev ++;
        }
        return prev;
    }
}
