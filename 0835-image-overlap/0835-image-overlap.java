class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        
        List<int[]> onesA = new ArrayList<>();
        List<int[]> onesB = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) onesA.add(new int[]{i, j});
                if (img2[i][j] == 1) onesB.add(new int[]{i, j});
            }
        }
        
        // shift range: -(n-1) to (n-1), offset by (n-1) so index is always >= 0
        int size = 2 * n - 1;
        int[][] count = new int[size][size];
        int maxOverlap = 0;
        
        for (int[] a : onesA) {
            for (int[] b : onesB) {
                int dx = b[0] - a[0] + (n - 1); // offset
                int dy = b[1] - a[1] + (n - 1); // offset
                count[dx][dy]++;
                maxOverlap = Math.max(maxOverlap, count[dx][dy]);
            }
        }
        
        return maxOverlap;
    }
}