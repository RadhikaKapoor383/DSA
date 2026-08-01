class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;

        // dp[i][j] = the maximum score difference (current player's score - other player's score)
        // the current player can achieve when playing optimally on the subarray nums[i..j]
        int[][] dp = new int[n][n];

        // Base case: single element, current player takes it entirely
        for (int i = 0; i < n; i++) {
            dp[i][i] = nums[i];
        }

        // Fill for increasing subarray lengths
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                // Choosing nums[i]: gain nums[i], then opponent plays optimally on (i+1, j),
                // so subtract their optimal difference dp[i+1][j]
                // Choosing nums[j]: gain nums[j], then opponent plays optimally on (i, j-1),
                // so subtract their optimal difference dp[i][j-1]
                dp[i][j] = Math.max(
                    nums[i] - dp[i + 1][j],
                    nums[j] - dp[i][j - 1]
                );
            }
        }

        // Player 1 wins (or ties) if the score difference is >= 0
        return dp[0][n - 1] >= 0;
    }
}