import java.util.*;

class Solution {

    static final long LIMIT = 1_000_001;

    List<Integer> primes = new ArrayList<>();

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        int[] half = new int[26];
        int halfLen = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];
            if ((freq[i] & 1) == 1)
                mid = (char) ('a' + i);
        }

        sieve(halfLen);

        if (countWays(half, halfLen) < k)
            return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            for (int c = 0; c < 26; c++) {

                if (half[c] == 0)
                    continue;

                half[c]--;

                long ways = countWays(half, halfLen - pos - 1);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                }

                k -= ways;
                half[c]++;
            }
        }

        StringBuilder ans = new StringBuilder();

        ans.append(left);

        if (mid != 0)
            ans.append(mid);

        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    void sieve(int n) {

        boolean[] prime = new boolean[n + 1];

        Arrays.fill(prime, true);

        for (int i = 2; i <= n; i++) {

            if (!prime[i])
                continue;

            primes.add(i);

            if ((long) i * i <= n) {

                for (int j = i * i; j <= n; j += i)
                    prime[j] = false;
            }
        }
    }

    int exponent(int n, int p) {

        int res = 0;

        while (n > 0) {
            n /= p;
            res += n;
        }

        return res;
    }

    long countWays(int[] cnt, int len) {

        long ans = 1;

        for (int p : primes) {

            int e = exponent(len, p);

            for (int x : cnt)
                e -= exponent(x, p);

            while (e-- > 0) {

                if (ans > LIMIT / p)
                    return LIMIT;

                ans *= p;
            }
        }

        return Math.min(ans, LIMIT);
    }
}