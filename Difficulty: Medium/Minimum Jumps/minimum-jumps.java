class Solution {
    static int minJumps(int[] arr) {
        int n = arr.length;

        if (n <= 1) return 0;
        if (arr[0] == 0) return -1;

        int jumps = 0;
        int pos = 0;

        while (pos < n - 1) {
            jumps++;

            int maxReach = -1;
            int nextPos = -1;

            // If we can directly reach or cross the end
            if (pos + arr[pos] >= n - 1) {
                return jumps;
            }

            // Explore all reachable positions from current index
            for (int i = pos + 1; i <= pos + arr[pos] && i < n; i++) {
                int reach = i + arr[i];
                if (reach > maxReach) {
                    maxReach = reach;
                    nextPos = i;
                }
            }

            // If no move possible (e.g., surrounded by 0s)
            if (nextPos == -1) return -1;

            pos = nextPos; // Jump to the best next position
        }

        return -1; // Shouldn't reach here normally
    }
}
