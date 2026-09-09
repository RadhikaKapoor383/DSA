class Solution {
    public int numberOfSets(int n, int k) {
        int MOD = 1_000_000_007;
        
        long[][] dp = new long[n + 1][k + 1];
        long[][] prefixSum = new long[n + 1][k + 1];
        
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
            prefixSum[i][0] = i + 1; // sum of (i+1) ones, for L=0..i
        }
        
        for (int j = 1; j <= k; j++) {
            for (int i = 0; i <= n; i++) {
                if (i == 0) {
                    dp[i][j] = 0;
                } else {
                    long sumL = (prefixSum[i - 1][j - 1] - dp[0][j - 1] + MOD) % MOD;
                    dp[i][j] = (dp[i - 1][j] + sumL) % MOD;
                }
                prefixSum[i][j] = ((i > 0 ? prefixSum[i - 1][j] : 0) + dp[i][j]) % MOD;
            }
        }
        
        return (int) dp[n][k];
    }
}