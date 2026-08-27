class Solution {
    public int rearrangeSticks(int n, int k) {
        int MOD = 1_000_000_007;
        long[][] dp = new long[n + 1][k + 1];
        dp[0][0] = 1;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                long fromFront = dp[i - 1][j - 1];
                long fromBehind = ((long)(i - 1) * dp[i - 1][j]) % MOD;
                dp[i][j] = (fromFront + fromBehind) % MOD;
            }
        }
        
        return (int) dp[n][k];
    }
}