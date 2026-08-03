class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;

        // dp[i] = max score difference (current player - opponent) achievable
        // by the player whose turn it is, considering stones from index i to n-1
        int[] dp = new int[n + 1];
        dp[n] = 0; // no stones left, difference is 0

        // Fill from the back since each state depends on future (later index) states
        for (int i = n - 1; i >= 0; i--) {
            int sum = 0;
            dp[i] = Integer.MIN_VALUE;

            // Try taking 1, 2, or 3 stones starting at i
            for (int k = 1; k <= 3 && i + k <= n; k++) {
                sum += stoneValue[i + k - 1]; // running sum of taken stones
                dp[i] = Math.max(dp[i], sum - dp[i + k]);
            }
        }

        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}