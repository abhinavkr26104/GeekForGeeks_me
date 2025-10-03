class Solution {

    static Boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;
        
        // dp[i][j] will be true if there exists a subset of the first i elements
        // that sums up to j
        boolean[][] dp = new boolean[n + 1][sum + 1];
        
        // Base case: sum = 0 can always be achieved by taking empty subset
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        
        // Fill the dp table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                // If we don't include arr[i-1]
                dp[i][j] = dp[i-1][j];
                
                // If we include arr[i-1], check remaining sum
                if (j >= arr[i-1]) {
                    dp[i][j] = dp[i][j] || dp[i-1][j - arr[i-1]];
                }
            }
        }
        
        return dp[n][sum];
    }

}
