class Solution {
    public boolean isPalindrome(int x) {
        // negatives and numbers ending in 0 (except 0 itself) aren't palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }

        // even length: x == reversed
        // odd length:  x == reversed/10 (middle digit doesn't matter)
        return x == reversed || x == reversed / 10;
    }
}