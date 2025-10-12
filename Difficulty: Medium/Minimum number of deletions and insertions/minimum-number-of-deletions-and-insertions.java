// User function Template for Java

class Solution {
    public int minOperations(String s1, String s2) {
        // Your code goes here
          int n = s1.length();
        int m = s2.length();

        // Create DP table
        int[][] dp = new int[n + 1][m + 1];

        // Fill DP table (Bottom-Up)
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    // If characters match, take diagonal + 1
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // Otherwise, take max of left or top
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Answer is at dp[n][m]
        return m+n-2*dp[n][m];
    }
}