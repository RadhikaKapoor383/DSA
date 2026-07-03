class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        health -= grid.get(0).get(0);
        if (health < 1) return false;

        Queue<int[]> q = new LinkedList<>();

        int[][] best = new int[m][n];
        for (int[] row : best)
            Arrays.fill(row, -1);

        best[0][0] = health;
        q.offer(new int[]{0,0,health});

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int r = cur[0];
            int c = cur[1];
            int hp = cur[2];

            if (r == m - 1 && c == n - 1)
                return true;

            for (int[] d : dirs) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;

                int newHp = hp - grid.get(nr).get(nc);

                if (newHp < 1)
                    continue;

                if (newHp <= best[nr][nc])
                    continue;

                best[nr][nc] = newHp;
                q.offer(new int[]{nr, nc, newHp});
            }
        }

        return false;
    }
}