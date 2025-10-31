class Solution {
    public int maximumPoints(int arr[][]) {
        int n = arr.length;
        int dp[][] = new int[n][4];
        
        // Base case: first day
        dp[0][0] = Math.max(arr[0][1], arr[0][2]);
        dp[0][1] = Math.max(arr[0][0], arr[0][2]);
        dp[0][2] = Math.max(arr[0][0], arr[0][1]);
        dp[0][3] = Math.max(arr[0][0], Math.max(arr[0][1], arr[0][2]));
        
        // Fill the DP table
        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {
                int maxi = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        int points = arr[day][task] + dp[day - 1][task];
                        maxi = Math.max(maxi, points);
                    }
                }
                dp[day][last] = maxi;
            }
        }
        
        return dp[n - 1][3];
    }
}
