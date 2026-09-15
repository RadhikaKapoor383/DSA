class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        
        // Precompute palindrome table: isPal[i][j] = true if s[i..j] is palindrome
        for (int i = n - 1; i >= 0; i--) {
            isPal[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i == 1 || isPal[i + 1][j - 1]) {
                        isPal[i][j] = true;
                    }
                }
            }
        }
        
        int[] dp = new int[n + 1];
        
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1]; // skip current character
            
            // try palindrome of length k ending at index i-1
            if (i >= k) {
                int start = i - k;
                if (isPal[start][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[start] + 1);
                }
            }
            
            // try palindrome of length k+1 ending at index i-1
            if (i >= k + 1) {
                int start = i - (k + 1);
                if (isPal[start][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[start] + 1);
                }
            }
        }
        
        return dp[n];
    }
}