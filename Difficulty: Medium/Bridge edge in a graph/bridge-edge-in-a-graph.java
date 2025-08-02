class Solution {
    void dfs(int node, boolean[] visited, List<List<Integer>> adj) {
        visited[node] = true;
        for (int nei : adj.get(node)) {
            if (!visited[nei]) {
                dfs(nei, visited, adj);
            }
        }
    }

    public boolean isBridge(int V, int[][] edges, int c, int d) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if ((u == c && v == d) || (u == d && v == c)) continue;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[V];
        dfs(c, visited, adj);

        return !visited[d];
    }
}
