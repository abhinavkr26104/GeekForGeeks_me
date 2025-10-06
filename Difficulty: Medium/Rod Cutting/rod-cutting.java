class Solution {
    public int cutRod(int[] price) {
        int n = price.length;
        int dp[] = new int[n + 1];

        // dp[len] = max profit for rod of length len
        for (int len = 1; len <= n; len++) {
            for (int cut = 1; cut <= len; cut++) {
                dp[len] = Math.max(dp[len], price[cut - 1] + dp[len - cut]);
            }
        }

        return dp[n];
    }
}
