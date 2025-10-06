class Solution {
    static int knapSack(int val[], int wt[], int capacity) {
        int n = val.length;
        int dp[] = new int[capacity + 1];

        // Build dp[] such that dp[c] stores max value for capacity c
        for (int c = 0; c <= capacity; c++) {
            for (int i = 0; i < n; i++) {
                if (wt[i] <= c) {
                    dp[c] = Math.max(dp[c], val[i] + dp[c - wt[i]]);
                }
            }
        }

        return dp[capacity];
    }
}
