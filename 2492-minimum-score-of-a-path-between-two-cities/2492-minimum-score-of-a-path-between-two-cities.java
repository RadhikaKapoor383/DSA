class Solution {
    public int minScore(int n, int[][] roads) {

        List<int[]>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] r : roads) {
            graph[r[0]].add(new int[]{r[1], r[2]});
            graph[r[1]].add(new int[]{r[0], r[2]});
        }

        int ans = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (int[] next : graph[node]) {
                int neighbor = next[0];
                int dist = next[1];

                ans = Math.min(ans, dist);

                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        return ans;
    }
}