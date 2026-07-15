class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;

        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2]; 
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '*') {
                    char prevPatternChar = p.charAt(j - 2);

                    boolean zeroOccurrence = dp[i][j - 2];

                    boolean oneOrMore = false;
                    if (prevPatternChar == '.' || prevPatternChar == sc) {
                        oneOrMore = dp[i - 1][j];
                    }

                    dp[i][j] = zeroOccurrence || oneOrMore;

                } else if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];

                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[m][n];
    }
}