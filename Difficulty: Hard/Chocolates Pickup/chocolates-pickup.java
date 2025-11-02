import java.util.*;

class Solution {
    public int solve(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // 3D dp: dp[row][col1][col2]
        int[][][] dp = new int[n][m][m];

        // Base case: last row
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                if (j1 == j2) dp[n - 1][j1][j2] = grid[n - 1][j1];
                else dp[n - 1][j1][j2] = grid[n - 1][j1] + grid[n - 1][j2];
            }
        }

        // Build dp bottom-up
        for (int i = n - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {

                    int maxChoco = Integer.MIN_VALUE;

                    // All 9 possible moves
                    for (int dj1 = -1; dj1 <= 1; dj1++) {
                        for (int dj2 = -1; dj2 <= 1; dj2++) {

                            int val = grid[i][j1];
                            if (j1 != j2) val += grid[i][j2];

                            int nextJ1 = j1 + dj1;
                            int nextJ2 = j2 + dj2;

                            if (nextJ1 >= 0 && nextJ1 < m && nextJ2 >= 0 && nextJ2 < m)
                                val += dp[i + 1][nextJ1][nextJ2];
                            else
                                val += Integer.MIN_VALUE; // invalid move

                            maxChoco = Math.max(maxChoco, val);
                        }
                    }
                    dp[i][j1][j2] = maxChoco;
                }
            }
        }

        // Start positions: Alice at (0,0), Bob at (0,m-1)
        return dp[0][0][m - 1];
    }
}
