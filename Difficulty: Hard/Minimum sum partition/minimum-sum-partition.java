class Solution {
    
    // Helper: check if subset with given sum exists
    static boolean isSubsetSum(int arr[], int sum) {
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i <= n; i++) {
            dp[i][0] = true; // sum = 0 always possible
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i-1][j]; // exclude
                if (arr[i-1] <= j) {
                    dp[i][j] = dp[i][j] || dp[i-1][j - arr[i-1]]; // include
                }
            }
        }
        return dp[n][sum];
    }

    // Main function
    public int minDifference(int arr[]) {
        int totalSum = 0;
        for (int num : arr) totalSum += num;

        int half = totalSum / 2;
        int s1 = 0;

        // Find largest subset sum <= totalSum/2
        for (int j = half; j >= 0; j--) {
            if (isSubsetSum(arr, j)) {
                s1 = j;
                break;
            }
        }

        return totalSum - 2 * s1;
    }

}
