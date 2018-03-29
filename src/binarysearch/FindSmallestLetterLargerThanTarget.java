package binarysearch;

/**
 * Created by yingtan on 1/20/18.
 *
 * 744. Find Smallest Letter Greater Than Target
 DescriptionHintsSubmissionsDiscussSolution
 DiscussPick One
 Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

 Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

 Examples:
 Input:
 letters = ["c", "f", "j"]
 target = "a"
 Output: "c"

 Input:
 letters = ["c", "f", "j"]
 target = "c"
 Output: "f"

 Input:
 letters = ["c", "f", "j"]
 target = "d"
 Output: "f"

 Input:
 letters = ["c", "f", "j"]
 target = "g"
 Output: "j"

 Input:
 letters = ["c", "f", "j"]
 target = "j"
 Output: "c"

 Input:
 letters = ["c", "f", "j"]
 target = "k"
 Output: "c"
 */
public class FindSmallestLetterLargerThanTarget {

    public char nextGreatestLetter(char[] letters, char target) {
        if (letters == null || letters.length == 0) return '.';
        int low = 0;
        int high = letters.length - 1;
        while(low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (letters[mid] <= target) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        if (letters[low] > target) {
            return letters[low];
        }
        else if (letters[high] > target) {
            return letters[high];
        }
        return letters[0];
    }
}
