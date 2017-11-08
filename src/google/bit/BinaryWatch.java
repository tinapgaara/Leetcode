package google.bit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yingtan on 8/8/17.
 *
 * 401. Binary Watch
 DescriptionHintsSubmissionsDiscussSolution
 Discuss Pick One
 A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

 Each LED represents a zero or one, with the least significant bit on the right.

 For example, the above binary watch reads "3:25".

 Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

 Example:

 Input: n = 1
 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]

 */
public class BinaryWatch {

    public List<String> readBinaryWatch(int num) {
        ArrayList<String> res = new ArrayList<>();
        // hours  : minutes
        // loop 12 hours, loop 60 minutes
        for (int h = 0; h < 12; h ++) {
            for (int m = 0 ; m < 60 ; m ++) {
                if (Integer.bitCount(h) + Integer.bitCount(m) == num) {
                    /*
                    %02d means "format the integer with 2 digits, left padding it with zeroes", so:

                    Format  Data   Result
                    %02d    1      01
                    %02d    11     11
                    */
                    res.add(String.format("%d:%02d", h, m));
                }
            }
        }
        return res;
    }

}
