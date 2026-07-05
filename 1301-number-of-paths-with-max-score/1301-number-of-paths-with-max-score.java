class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;

        int[][] maxSum = new int[n][n];
        int[][] ways = new int[n][n];

        ways[n-1][n-1] = 1;

        for (int r = n-1; r >= 0; r--) {
            for (int c = n-1; c >= 0; c--) {
                if (board.get(r).charAt(c) == 'X') continue;
                if (r == n-1 && c == n-1) continue;

                int[][] dirs = {{1,0},{0,1},{1,1}};
                for (int[] d : dirs) {
                    int pr = r + d[0];
                    int pc = c + d[1];

                    if (pr >= n || pc >= n) continue;
                    if (ways[pr][pc] == 0) continue;

                    int val = maxSum[pr][pc];
                    if (val > maxSum[r][c]) {
                        maxSum[r][c] = val;
                        ways[r][c] = ways[pr][pc];
                    } else if (val == maxSum[r][c]) {
                        ways[r][c] = (ways[r][c] + ways[pr][pc]) % MOD;
                    }
                }

                char ch = board.get(r).charAt(c);
                if (ch >= '1' && ch <= '9') {
                    maxSum[r][c] += (ch - '0');
                }
            }
        }

        if (ways[0][0] == 0) return new int[]{0, 0};
        return new int[]{maxSum[0][0], ways[0][0]};
    }
}