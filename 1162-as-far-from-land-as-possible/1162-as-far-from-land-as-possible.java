class Solution {
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Queue<int[]> queue = new LinkedList<>();
        
        // Add all land cells as initial BFS sources
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
            }
        }
        
        // Edge case: no land or no water
        if (queue.isEmpty() || queue.size() == n * n) return -1;
        
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        int distance = -1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            
            for (int k = 0; k < size; k++) {
                int[] cell = queue.poll();
                int r = cell[0], c = cell[1];
                
                for (int[] d : dirs) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 0) {
                        grid[nr][nc] = 1; // mark visited (reuse grid as visited marker)
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }
        
        return distance;
    }
}