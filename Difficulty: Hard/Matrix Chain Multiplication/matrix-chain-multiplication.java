class Solution {
    static int matrixMultiplication(int arr[]) {
        int n = arr.length;
        int dp[][] = new int[n][n];
        
        // initialize dp array with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        
        return solve(arr, 1, n - 1, dp);
    }
    
    static int solve(int arr[], int i, int j, int dp[][]) {
        // base case: one matrix
        if (i == j)
            return 0;
        
        if (dp[i][j] != -1)
            return dp[i][j];
        
        int minCost = Integer.MAX_VALUE;
        
        for (int k = i; k < j; k++) {
            int cost1 = solve(arr, i, k, dp);
            int cost2 = solve(arr, k + 1, j, dp);
            int costMultiply = arr[i - 1] * arr[k] * arr[j];
            
            int totalCost = cost1 + cost2 + costMultiply;
            
            minCost = Math.min(minCost, totalCost);
        }
        
        dp[i][j] = minCost;
        return minCost;
    }
}
