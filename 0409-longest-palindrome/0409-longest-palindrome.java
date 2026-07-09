class Solution {
    public int longestPalindrome(String s) {
        int[] freq = new int[128];
        for (char c : s.toCharArray()) freq[c]++;

        int length = 0;
        boolean oddFound = false;

        for (int count : freq) {
            length += (count / 2) * 2;
            if (count % 2 == 1) oddFound = true;
        }

        return oddFound ? length + 1 : length;
    }
}