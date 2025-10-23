import java.util.*;

class Solution {
    static Map<String, Boolean> dp = new HashMap<>();

    static boolean isScramble(String S1, String S2) {
        // Base 1: identical strings
        if (S1.equals(S2))
            return true;

        int n = S1.length();
        if (n != S2.length())
            return false;

        // Key for memoization
        String key = S1 + "_" + S2;

        if (dp.containsKey(key))
            return dp.get(key);

        // Pruning: if characters don't match, it's not scramble
        char[] a1 = S1.toCharArray();
        char[] a2 = S2.toCharArray();
        Arrays.sort(a1);
        Arrays.sort(a2);
        if (!Arrays.equals(a1, a2)) {
            dp.put(key, false);
            return false;
        }

        boolean flag = false;

        // Try every possible split
        for (int i = 1; i < n; i++) {
            // Case 1: No swap
            boolean noSwap = isScramble(S1.substring(0, i), S2.substring(0, i)) &&
                             isScramble(S1.substring(i), S2.substring(i));

            // Case 2: With swap
            boolean swap = isScramble(S1.substring(0, i), S2.substring(n - i)) &&
                           isScramble(S1.substring(i), S2.substring(0, n - i));

            if (noSwap || swap) {
                flag = true;
                break;
            }
        }

        dp.put(key, flag);
        return flag;
    }

    public static void main(String[] args) {
        String S1 = "great";
        String S2 = "rgeat";
        System.out.println("Is Scramble? " + isScramble(S1, S2)); // ✅ true

        S1 = "abcde";
        S2 = "caebd";
        System.out.println("Is Scramble? " + isScramble(S1, S2)); // ❌ false
    }
}
