class Solution {
    private static final int MOD = 1_000_000_007;
    private static final int MAX_VAL = 200;

    public int subsequencePairCount(int[] nums) {
        
        int[][] gcdTable = new int[MAX_VAL + 1][MAX_VAL + 1];
        for (int i = 0; i <= MAX_VAL; i++) {
            for (int j = 0; j <= MAX_VAL; j++) {
                gcdTable[i][j] = gcd(i, j);
            }
        }

        long[][] dp = new long[MAX_VAL + 1][MAX_VAL + 1];
        dp[0][0] = 1;

        for (int num : nums) {
            long[][] newDp = new long[MAX_VAL + 1][MAX_VAL + 1];

            for (int g1 = 0; g1 <= MAX_VAL; g1++) {
                System.arraycopy(dp[g1], 0, newDp[g1], 0, MAX_VAL + 1);
            }

            for (int g1 = 0; g1 <= MAX_VAL; g1++) {
                for (int g2 = 0; g2 <= MAX_VAL; g2++) {
                    long val = dp[g1][g2];
                    if (val == 0) continue;

                    int ng1 = gcdTable[g1][num];
                    int ng2 = gcdTable[g2][num];

                    newDp[ng1][g2] = (newDp[ng1][g2] + val) % MOD;
                    newDp[g1][ng2] = (newDp[g1][ng2] + val) % MOD;
                }
            }

            dp = newDp;
        }

        long answer = 0;
        for (int g = 1; g <= MAX_VAL; g++) {
            answer = (answer + dp[g][g]) % MOD;
        }

        return (int) answer;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}