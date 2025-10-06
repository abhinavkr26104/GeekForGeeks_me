class Solution {
    public int count(int coins[], int sum) {
        int n = coins.length;
        int dp[] = new int[sum + 1];

        // Base case: one way to make sum = 0 (choose nothing)
        dp[0] = 1;

        // For each coin, update dp[]
        for (int i = 0; i < n; i++) {
            for (int s = coins[i]; s <= sum; s++) {
                dp[s] += dp[s - coins[i]];
            }
        }

        return dp[sum];
    }
}
