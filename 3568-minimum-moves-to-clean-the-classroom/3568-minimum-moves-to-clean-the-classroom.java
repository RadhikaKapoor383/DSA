class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) grid[i] = classroom[i].toCharArray();
        
        int startR = -1, startC = -1;
        int[][] litterIndex = new int[m][n];
        for (int[] row : litterIndex) Arrays.fill(row, -1);
        int litterCount = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    startR = i;
                    startC = j;
                } else if (grid[i][j] == 'L') {
                    litterIndex[i][j] = litterCount++;
                }
            }
        }
        
        int fullMask = (litterCount == 0) ? 0 : (1 << litterCount) - 1;
        
        // if starting state already satisfies (no litter), answer is 0
        if (fullMask == 0) return 0;
        
        int maskSize = 1 << litterCount;
        int energyLevels = energy + 1;
        
        // visited[r][c][e][mask]
        boolean[][][][] visited = new boolean[m][n][energyLevels][maskSize];
        
        Queue<int[]> queue = new LinkedList<>();
        // state: {r, c, e, mask, steps}
        queue.offer(new int[]{startR, startC, energy, 0, 0});
        visited[startR][startC][energy][0] = true;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0], c = cur[1], e = cur[2], mask = cur[3], steps = cur[4];
            
            if (mask == fullMask) {
                return steps;
            }
            
            if (e == 0) continue; // stuck, can't move further
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                
                if (nr < 0 || nr >= m || nc < 0 || nc >= n) continue;
                if (grid[nr][nc] == 'X') continue;
                
                int newEnergy = (grid[nr][nc] == 'R') ? energy : (e - 1);
                int newMask = mask;
                if (grid[nr][nc] == 'L' && litterIndex[nr][nc] != -1) {
                    newMask |= (1 << litterIndex[nr][nc]);
                }
                
                if (!visited[nr][nc][newEnergy][newMask]) {
                    visited[nr][nc][newEnergy][newMask] = true;
                    queue.offer(new int[]{nr, nc, newEnergy, newMask, steps + 1});
                }
            }
        }
        
        return -1;
    }
}