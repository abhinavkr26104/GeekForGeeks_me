// User function Template for Java
import java.util.*;
class Solution {

    class DisjointSet {
        int[] parent;
        int[] size;

        public DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int findPar(int node) {
            if (parent[node] != node) {
                parent[node] = findPar(parent[node]);
            }
            return parent[node];
        }

        void unionBySize(int u, int v) {
            int pu = findPar(u);
            int pv = findPar(v);
            if (pu == pv) return;
            if (size[pu] < size[pv]) {
                parent[pu] = pv;
                size[pv] += size[pu];
            } else {
                parent[pv] = pu;
                size[pu] += size[pv];
            }
        }
    }

    public List<Integer> numOfIslands(int rows, int cols, int[][] operators) {
        List<Integer> result = new ArrayList<>();
        DisjointSet ds = new DisjointSet(rows * cols);
        boolean[][] visited = new boolean[rows][cols];
        int count = 0;

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

        for (int[] op : operators) {
            int row = op[0], col = op[1];

            if (visited[row][col]) {
                result.add(count);
                continue;
            }

            visited[row][col] = true;
            count++;

            int node = row * cols + col;

            for (int[] d : directions) {
                int newRow = row + d[0];
                int newCol = col + d[1];

                if (newRow < 0 || newCol < 0 || newRow >= rows || newCol >= cols)
                    continue;
                if (!visited[newRow][newCol]) continue;

                int adjNode = newRow * cols + newCol;

                if (ds.findPar(node) != ds.findPar(adjNode)) {
                    ds.unionBySize(node, adjNode);
                    count--;
                }
            }

            result.add(count);
        }

        return result;
    }
}
