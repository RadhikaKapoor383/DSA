class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        long minCoin = Long.MAX_VALUE;
        for (int c : coins) minCoin = Math.min(minCoin, c);
        
        long lo = 1;
        long hi = minCoin * k; // upper bound: using only the smallest coin gives k-th multiple
        
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (countAtMost(mid, coins) >= k) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        
        return lo;
    }
    
    // Count how many positive integers <= x are multiples of at least one coin
    private long countAtMost(long x, int[] coins) {
        int n = coins.length;
        long count = 0;
        
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcmVal = 1;
            int bits = Integer.bitCount(mask);
            boolean overflowed = false;
            
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    lcmVal = lcm(lcmVal, coins[i], x);
                    if (lcmVal > x) {
                        overflowed = true;
                        break;
                    }
                }
            }
            
            if (overflowed) continue;
            
            long term = x / lcmVal;
            if (bits % 2 == 1) {
                count += term;
            } else {
                count -= term;
            }
        }
        
        return count;
    }
    
    private long lcm(long a, long b, long cap) {
        long g = gcd(a, b);
        long result = a / g;
        // check overflow before multiplying
        if (result > cap / b) return cap + 1; // sentinel: exceeds cap
        return result * b;
    }
    
    private long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}