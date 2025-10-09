import java.util.Arrays;

class Solution {
    public int minCoins(int coins[], int sum) {
        int n = coins.length;
        int INF = 1000000; // large value for "infinity"
        
        int dp[][] = new int[n + 1][sum + 1];
        
        // Initialize
        for (int j = 1; j <= sum; j++) {
            dp[0][j] = INF; // cannot form sum with 0 coins
        }
        
        // Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (coins[i - 1] <= j) {
                    dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i - 1]]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        
        return dp[n][sum] >= INF ? -1 : dp[n][sum];
    }
}
