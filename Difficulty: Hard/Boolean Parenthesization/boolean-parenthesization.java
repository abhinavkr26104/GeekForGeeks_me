import java.util.*;

class Solution {
    static int countWays(String s) {
        int n = s.length();
        Map<String, Integer> dp = new HashMap<>();
        return solve(s, 0, n - 1, true, dp);
    }

    static int solve(String s, int i, int j, boolean isTrue, Map<String, Integer> dp) {
        // Base cases
        if (i > j) return 0;
        if (i == j) {
            if (isTrue)
                return s.charAt(i) == 'T' ? 1 : 0;
            else
                return s.charAt(i) == 'F' ? 1 : 0;
        }

        // Memoization key
        String key = i + "_" + j + "_" + isTrue;
        if (dp.containsKey(key))
            return dp.get(key);

        int ways = 0;

        // Try every operator position
        for (int k = i + 1; k <= j - 1; k += 2) {
            char op = s.charAt(k);

            // Left and right subproblems
            int leftTrue = solve(s, i, k - 1, true, dp);
            int leftFalse = solve(s, i, k - 1, false, dp);
            int rightTrue = solve(s, k + 1, j, true, dp);
            int rightFalse = solve(s, k + 1, j, false, dp);

            // Apply boolean logic
            if (op == '&') {
                if (isTrue)
                    ways += leftTrue * rightTrue;
                else
                    ways += leftTrue * rightFalse + leftFalse * rightTrue + leftFalse * rightFalse;
            } else if (op == '|') {
                if (isTrue)
                    ways += leftTrue * rightTrue + leftTrue * rightFalse + leftFalse * rightTrue;
                else
                    ways += leftFalse * rightFalse;
            } else if (op == '^') {
                if (isTrue)
                    ways += leftTrue * rightFalse + leftFalse * rightTrue;
                else
                    ways += leftTrue * rightTrue + leftFalse * rightFalse;
            }
        }

        dp.put(key, ways);
        return ways;
    }

    // Example test
    public static void main(String[] args) {
        String s = "T|F&T^T";
        System.out.println("Number of ways to make expression TRUE: " + countWays(s));
    }
}
