package amazon;
import java.util.*;
public class ReorderLogs {
    public List<String> reorderLog(int logFileSize, List<String> logLines) {
        LogComparator comp = new LogComparator();
        Collections.sort(logLines, comp);
        return logLines;
    }

    public class LogComparator implements Comparator<String> {
        public int compare(String log1, String log2) {
            boolean isInteger1 = isInteger(log1);
            boolean isInteger2 = isInteger(log2);
            if (isInteger1 && isInteger2) {
                return 0; // same
            }
            if (isInteger1) {
                return 1; // log2 > log1
            }
            if (isInteger2) {
                return -1; // log1 > log2
            }
            // check if is tie
            String[] headAndBody1 = getHeadAndBody(log1);
            String[] headAndBody2 = getHeadAndBody(log2);
            if (headAndBody1[1].equals(headAndBody2[1])) {
                // exactly the same, compare head(identifier)
                return headAndBody1[0].compareTo(headAndBody2[0]);
            }
            else {
                // compare body
                return headAndBody1[1].compareTo(headAndBody2[1]);
            }
        }
    }
    private boolean isInteger(String log) {
        int index = log.lastIndexOf(" ");
        String lastPart = log.substring(index + 1);
        // If the last part are all digits, then, must be digits.
        return lastPart.matches("[0-9]+");
    }
    private String[] getHeadAndBody(String log) {
        int index = log.indexOf(" ");
        String head = log.substring(0, index);
        String body = log.substring(index + 1);
        return new String[]{head, body};
    }

    public static void main(String[] args) {
        String[] a = {
                "a1 9 2 3 1",
                "g1 Act car",
                "zo4 4 7",
                "ab1 off KEY dog",
                "a8 act zoo"
        };
        String[] b = {
                "mi2 jog mid pet",
                "wz3 34 54 398",
                "a1 alps cow bar",
                "x4 45 21 7"
        };
        String[] c = {
                "t2 13 121 98",
                "r1 box ape bit",
                "b4 xi me nu",
                "br8 eat nim did",
                "w1 has uni gry",
                "f3 52 54 31"
        };
        String[] d = {
                "a1 9 2 3 1",
                "g1 act car",
                "zo4 4 7",
                "ab1 off KEY dog",
                "a2 a c t c a r "
        };
        List<String> logsa = Arrays.asList(a);
        List<String> logsb = Arrays.asList(b);
        List<String> logsc = Arrays.asList(c);
        List<String> logsd = Arrays.asList(d);
        ReorderLogs ob = new ReorderLogs();
        System.out.println(ob.reorderLog(logsa.size(), logsa));
        System.out.println(ob.reorderLog(logsb.size(), logsb));
        System.out.println(ob.reorderLog(logsc.size(), logsc));
        System.out.println(ob.reorderLog(logsd.size(), logsd));
    }
}
