import java.util.*;

class Solution {

    public int kosaraju(ArrayList<ArrayList<Integer>> adj) {
        int V = adj.size();
        ArrayList<ArrayList<Integer>> transpose = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            transpose.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
            for (int v : adj.get(i)) {
                transpose.get(v).add(i);
            }
        }

        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs1(i, adj, visited, stack);
            }
        }

        Arrays.fill(visited, false);
        int sccCount = 0;

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                dfs2(node, transpose, visited);
                sccCount++;
            }
        }

        return sccCount;
    }

    private void dfs1(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int v : adj.get(node)) {
            if (!visited[v]) {
                dfs1(v, adj, visited, stack);
            }
        }
        stack.push(node);
    }

    private void dfs2(int node, ArrayList<ArrayList<Integer>> transpose, boolean[] visited) {
        visited[node] = true;
        for (int v : transpose.get(node)) {
            if (!visited[v]) {
                dfs2(v, transpose, visited);
            }
        }
    }
}