class Solution {
    private int time = 0;

    public ArrayList<Integer> articulationPoints(int V, ArrayList<ArrayList<Integer>> adj) {
        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] visited = new boolean[V];
        boolean[] isAP = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(i, -1, disc, low, visited, isAP, adj);
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (isAP[i]) result.add(i);
        }

        if (result.size() == 0) result.add(-1);
        return result;
    }

    private void dfs(int u, int parent, int[] disc, int[] low, boolean[] visited,
                     boolean[] isAP, ArrayList<ArrayList<Integer>> adj) {
        visited[u] = true;
        disc[u] = low[u] = ++time;
        int children = 0;

        for (int v : adj.get(u)) {
            if (v == parent) continue;

            if (!visited[v]) {
                children++;
                dfs(v, u, disc, low, visited, isAP, adj);
                low[u] = Math.min(low[u], low[v]);

                if (parent != -1 && low[v] >= disc[u]) {
                    isAP[u] = true;
                }
            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        if (parent == -1 && children > 1) {
            isAP[u] = true;
        }
    }
}
