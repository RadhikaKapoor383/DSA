class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        Integer[] sorted = new Integer[n];
        for (int i = 0; i < n; i++) sorted[i] = i;
        Arrays.sort(sorted, (a, b) -> nums[a] - nums[b]);

        int[] rank = new int[n];
        int[] sortedNums = new int[n];
        for (int i = 0; i < n; i++) {
            rank[sorted[i]] = i;
            sortedNums[i] = nums[sorted[i]];
        }

        // for each node in sorted order, find furthest reachable index in one hop
        int[] reach = new int[n];
        int right = 0;
        for (int left = 0; left < n; left++) {
            if (right < left) right = left;
            while (right + 1 < n && sortedNums[right + 1] - sortedNums[left] <= maxDiff) right++;
            reach[left] = right;
        }

        // binary lifting: jump[k][i] = furthest index reachable from i in 2^k hops
        int LOG = 17;
        int[][] jump = new int[LOG][n];
        jump[0] = reach;
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                jump[k][i] = jump[k-1][jump[k-1][i]];
            }
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0], v = queries[i][1];

            if (u == v) { answer[i] = 0; continue; }

            int ru = rank[u], rv = rank[v];
            if (ru > rv) { int tmp = ru; ru = rv; rv = tmp; }

            // check if reachable at all
            if (reach[ru] < rv) {
                // try lifting ru to reach rv
                int cur = ru, dist = 0;
                for (int k = LOG - 1; k >= 0; k--) {
                    if (jump[k][cur] < rv) {
                        cur = jump[k][cur];
                        dist += (1 << k);
                    }
                }
                // one more hop needed
                if (reach[cur] >= rv) {
                    answer[i] = dist + 1;
                } else {
                    answer[i] = -1;
                }
            } else {
                answer[i] = 1; // directly reachable in one hop
            }
        }

        return answer;
    }
}