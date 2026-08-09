class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }
        
        Integer[][] memo = new Integer[n][n + 1];
        return dp(0, 1, piles, suffixSum, memo, n);
    }
    
    private int dp(int i, int M, int[] piles, int[] suffixSum, Integer[][] memo, int n) {
        if (i >= n) return 0;
        
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }
        
        if (memo[i][M] != null) return memo[i][M];
        
        int best = 0;
        for (int X = 1; X <= 2 * M; X++) {
            int newM = Math.max(M, X);
            int taken = suffixSum[i] - dp(i + X, newM, piles, suffixSum, memo, n);
            best = Math.max(best, taken);
        }
        
        memo[i][M] = best;
        return best;
    }
}