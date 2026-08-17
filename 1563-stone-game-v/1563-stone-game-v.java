class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }
        
        int[][] dp = new int[n][n];
        // dp[i][i] = 0 by default (Java arrays init to 0)
        
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                int best = 0;
                
                for (int k = i; k < j; k++) {
                    int leftSum = prefix[k + 1] - prefix[i];
                    int rightSum = prefix[j + 1] - prefix[k + 1];
                    
                    int candidate;
                    if (leftSum < rightSum) {
                        candidate = leftSum + dp[i][k];
                    } else if (leftSum > rightSum) {
                        candidate = rightSum + dp[k + 1][j];
                    } else {
                        candidate = leftSum + Math.max(dp[i][k], dp[k + 1][j]);
                    }
                    
                    best = Math.max(best, candidate);
                }
                
                dp[i][j] = best;
            }
        }
        
        return dp[0][n - 1];
    }
}