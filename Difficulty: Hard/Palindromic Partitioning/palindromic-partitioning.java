class Solution {
    static int palPartition(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        int[] dp = new int[n];

        // Precompute palindrome table
        for (int i = 0; i < n; i++) {
            isPal[i][i] = true;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2) isPal[i][j] = true;
                    else isPal[i][j] = isPal[i + 1][j - 1];
                } else {
                    isPal[i][j] = false;
                }
            }
        }

        // dp[i] = min cuts for s[0..i]
        for (int i = 0; i < n; i++) {
            if (isPal[0][i]) {
                dp[i] = 0;  // no cut needed
            } else {
                dp[i] = i;  // worst case: cut before every char
                for (int j = 1; j <= i; j++) {
                    if (isPal[j][i]) {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        String s = "ababbbabbababa";
        System.out.println("Minimum cuts needed: " + palPartition(s));
    }
}
