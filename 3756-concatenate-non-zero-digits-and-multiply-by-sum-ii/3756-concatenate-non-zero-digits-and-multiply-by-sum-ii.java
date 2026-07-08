class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int MOD = 1_000_000_007;
        int n = s.length();

        // prefixSum[i] = sum of non-zero digits from 0..i-1
        long[] prefixSum = new long[n + 1];

        // prefixX[i] = x value (concatenated non-zero digits) from 0..i-1
        long[] prefixX = new long[n + 1];

        // power10[i] = 10^(number of non-zero digits from 0..i-1)
        long[] power10 = new long[n + 1];
        power10[0] = 1;

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';
            if (digit != 0) {
                prefixX[i + 1]   = (prefixX[i] * 10 + digit) % MOD;
                prefixSum[i + 1] = prefixSum[i] + digit;
                power10[i + 1]   = (power10[i] * 10) % MOD;
            } else {
                prefixX[i + 1]   = prefixX[i];
                prefixSum[i + 1] = prefixSum[i];
                power10[i + 1]   = power10[i];
            }
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            // x for [l..r] = x[l..r] = prefixX[r+1] - prefixX[l] * power10[r+1-l]
            long x   = (prefixX[r + 1] - prefixX[l] * power10[r + 1] % MOD * modInverse(power10[l], MOD) % MOD + MOD * 2) % MOD;
            long sum = prefixSum[r + 1] - prefixSum[l];

            answer[i] = (int) (x * (sum % MOD) % MOD);
        }

        return answer;
    }

    private long modInverse(long a, long mod) {
        return modPow(a, mod - 2, mod);
    }

    private long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }
}