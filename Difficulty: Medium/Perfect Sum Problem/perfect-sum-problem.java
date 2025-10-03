class Solution {
    // Function to calculate the number of subsets with a given sum
    public int perfectSum(int[] nums, int target) {
        int n = nums.length;
        int MOD = 1000000007; // usually required in contests
        
        // dp[i][j] = number of subsets using first i items with sum = j
        int[][] dp = new int[n + 1][target + 1];

        // Base case: one way to form sum = 0 (empty set)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                // Exclude nums[i-1]
                dp[i][j] = dp[i-1][j];

                // Include nums[i-1] (if possible)
                if (nums[i-1] <= j) {
                    dp[i][j] = (dp[i][j] + dp[i-1][j - nums[i-1]]) % MOD;
                }
            }
        }

        return dp[n][target];
    }

  
}
