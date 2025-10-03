class Solution {
    // Subset Sum (Knapsack-based)
    static boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        // Base case: sum = 0 possible with empty subset
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                if (arr[i-1] <= j) {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j - arr[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[n][sum];
    }

    // Equal Partition
    static boolean equalPartition(int arr[]) {
        int totalSum = 0;
        for (int num : arr) {
            totalSum += num;
        }

        // If sum is odd → cannot partition equally
        if (totalSum % 2 != 0) return false;

        // Else check subset sum for half of total
        return isSubsetSum(arr, totalSum / 2);
    }


}
