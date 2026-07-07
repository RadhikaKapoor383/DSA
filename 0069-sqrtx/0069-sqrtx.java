class Solution {
    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;

        int lo = 1, hi = x / 2, ans = 0;

        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;

            if (mid * mid == x) return (int) mid;
            else if (mid * mid < x) {
                ans = (int) mid;
                lo = (int) mid + 1;
            } else {
                hi = (int) mid - 1;
            }
        }

        return ans;
    }
}