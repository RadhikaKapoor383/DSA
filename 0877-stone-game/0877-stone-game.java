class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;

        // dp[i][j] = max score difference (current player - opponent)
        // the current player can achieve on subarray piles[i..j]
        int[][] dp = new int[n][n];

        // Base case: single pile, current player takes it entirely
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }

        // Fill for increasing subarray lengths
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(
                    piles[i] - dp[i + 1][j],
                    piles[j] - dp[i][j - 1]
                );
            }
        }

        // Alice wins if her score difference is positive
        // (guaranteed no ties since total sum is odd)
        return dp[0][n - 1] > 0;
    }
}