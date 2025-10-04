class Solution {

    static int MOD = 1000000007;

    // Reuse isSubsetSum logic — but modified to count subsets
    static int countSubsetsWithSum(int arr[], int sum) {
        int n = arr.length;
        int[][] dp = new int[n + 1][sum + 1];

        // Base case: 1 way to make sum 0 (empty subset)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // Fill dp table like isSubsetSum
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                // Exclude current element
                dp[i][j] = dp[i-1][j] % MOD;

                // Include current element (if possible)
                if (arr[i-1] <= j) {
                    dp[i][j] = (dp[i][j] + dp[i-1][j - arr[i-1]]) % MOD;
                }
            }
        }

        return dp[n][sum];
    }

    // Main function using the helper (isSubsetSum pattern reused)
    int countPartitions(int[] arr, int d) {
        int totalSum = 0;
        for (int num : arr) totalSum += num;

        // If invalid (odd or smaller)
        if ((d + totalSum) % 2 != 0 || totalSum < d) return 0;

        int target = (d + totalSum) / 2;
        return countSubsetsWithSum(arr, target);
    }

    // Testing
    
}
