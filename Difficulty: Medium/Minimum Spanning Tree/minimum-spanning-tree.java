import java.util.*;

class Solution {
    static int spanningTree(int V, int E, List<List<int[]>> adj) {
        boolean[] selected = new boolean[V];
        int[] minWeight = new int[V];
        Arrays.fill(minWeight, Integer.MAX_VALUE);
        minWeight[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.add(new int[]{0, 0});

        int totalWeight = 0;

        for (int i = 0; i < V; ) {
            if (pq.isEmpty()) return -1;

            int[] top = pq.poll();
            int weight = top[0];
            int u = top[1];

            if (selected[u]) continue;

            selected[u] = true;
            totalWeight += weight;
            i++;

            for (int[] edge : adj.get(u)) {
                int v = edge[0], w = edge[1];
                if (!selected[v] && w < minWeight[v]) {
                    minWeight[v] = w;
                    pq.add(new int[]{w, v});
                }
            }
        }

        return totalWeight;
    }
}
