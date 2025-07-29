class Solution {
    public void floydWarshall(int[][] dist) {
        int n = dist.length;
        final int INF = 100000000; // representing infinity

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    // Only update if both paths are not "infinite"
                    if (dist[i][k] < INF && dist[k][j] < INF) {
                        // Use long to avoid overflow during addition
                        long newDist = (long) dist[i][k] + dist[k][j];
                        if (newDist < dist[i][j]) {
                            dist[i][j] = (int) newDist;
                        }
                    }
                }
            }
        }
    }
}
