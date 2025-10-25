class Solution {
    static int[][] dp;

    static int eggDrop(int n, int k) {
        dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++)
            for (int j = 0; j <= k; j++)
                dp[i][j] = -1;
        return solve(n, k);
    }

    static int solve(int n, int k) {
        if (k == 0 || k == 1)
            return k;
        if (n == 1)
            return k;
        if (dp[n][k] != -1)
            return dp[n][k];

        int low = 1, high = k, ans = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            int breakCase = solve(n - 1, mid - 1);
            int noBreakCase = solve(n, k - mid);
            int worst = 1 + Math.max(breakCase, noBreakCase);

            if (breakCase > noBreakCase) {
                high = mid - 1;
                ans = Math.min(ans, worst);
            } else {
                low = mid + 1;
                ans = Math.min(ans, worst);
            }
        }

        return dp[n][k] = ans;
    }

    public static void main(String[] args) {
        System.out.println(eggDrop(2, 100));  // ✅ 14
        System.out.println(eggDrop(3, 100));  // ✅ 9
    }
}
