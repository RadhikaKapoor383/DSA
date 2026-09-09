class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long lower = 1; // start of digit-length group (d=1 starts at 1)
        int d = 1;
        
        while (lower <= n) {
            long upper = lower * 10 - 1; // largest number with d digits, e.g. d=1 -> 9, d=2 -> 99
            long rangeEnd = Math.min(n, upper);
            
            long countInGroup = rangeEnd - lower + 1;
            int commasPerNumber = (d - 1) / 3;
            
            totalCommas += countInGroup * commasPerNumber;
            
            lower = upper + 1; // move to next digit-length group
            d++;
        }
        
        return totalCommas;
    }
}