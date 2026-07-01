class Solution {
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        int[][] dist = new int[n][n];

        // fill with -1
        for (int[] row : dist) Arrays.fill(row, -1);

        // multi-source BFS from all thieves
        Queue<int[]> queue = new LinkedList<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) {
                    dist[r][c] = 0;
                    queue.offer(new int[]{r, c});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] d : dirs) {
                int nr = curr[0] + d[0];
                int nc = curr[1] + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[curr[0]][curr[1]] + 1;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        // binary search on safeness value
        int lo = 0, hi = 2 * n, ans = 0;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (canReach(dist, n, mid)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean canReach(int[][] dist, int n, int safeness) {
        if (dist[0][0] < safeness || dist[n-1][n-1] < safeness) return false;

        boolean[][] visited = new boolean[n][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == n-1 && curr[1] == n-1) return true;

            for (int[] d : dirs) {
                int nr = curr[0] + d[0];
                int nc = curr[1] + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n 
                    && !visited[nr][nc] && dist[nr][nc] >= safeness) {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return false;
    }
}