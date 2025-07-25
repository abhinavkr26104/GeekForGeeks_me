class Solution {

    public int[] shortestPath(int V, int E, int[][] edges) {
        ArrayList<ArrayList<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }

        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topoSort(i, visited, stack, adj);
            }
        }

        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (dist[u] != Integer.MAX_VALUE) {
                for (int[] neighbor : adj.get(u)) {
                    int v = neighbor[0];
                    int wt = neighbor[1];
                    if (dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                    }
                }
            }
        }

        for (int i = 0; i < V; i++) {
            if (dist[i] == Integer.MAX_VALUE) dist[i] = -1;
        }

        return dist;
    }

    private void topoSort(int node, boolean[] visited, Stack<Integer> stack, ArrayList<ArrayList<int[]>> adj) {
        visited[node] = true;
        for (int[] neighbor : adj.get(node)) {
            int v = neighbor[0];
            if (!visited[v]) {
                topoSort(v, visited, stack, adj);
            }
        }
        stack.push(node);
    }
}
