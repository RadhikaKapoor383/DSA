class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int n = s.length();
        long[] dp = new long[n + 1];
        dp[0] = 1; // represents the "empty subsequence" baseline for the recurrence
        
        long[] last = new long[26]; // last[c] = dp value (before increment) recorded when c was last processed
        Arrays.fill(last, -1);
        
        for (int i = 1; i <= n; i++) {
            char c = s.charAt(i - 1);
            int idx = c - 'a';
            
            dp[i] = (2 * dp[i - 1]) % MOD;
            
            if (last[idx] != -1) {
                dp[i] = (dp[i] - last[idx] + MOD) % MOD;
            }
            
            last[idx] = dp[i - 1];
            dp[i - 1] = dp[i - 1]; // no-op, just clarity that we captured it before this line potentially changes usage
        }
        
        // dp[n] counts all subsequences including the empty one (since dp[0]=1 baseline),
        // so subtract 1 for the empty subsequence
        return (int) ((dp[n] - 1 + MOD) % MOD);
    }
}