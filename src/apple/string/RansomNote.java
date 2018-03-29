package apple.string;

public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        // if magazine contains ransomNote
        // aa aba -> true, ca abc -> true
        if (ransomNote == null || magazine == null) return false;
        if (ransomNote.length() > magazine.length()) return false;
        int[] countNote = new int[26];
        for (char ch : ransomNote.toCharArray()) {
            countNote[ch - 'a'] ++;
        }
        int[] magCount = new int[26];
        for (char ch : magazine.toCharArray()) {
            magCount[ch - 'a'] ++;
        }
        for (int i = 0 ; i < 26; i ++) {
            if (countNote[i] > magCount[i]) {
                return false;
            }
        }
        return true;
    }
}
