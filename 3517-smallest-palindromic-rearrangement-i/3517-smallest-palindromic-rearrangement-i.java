class Solution {
    public String smallestPalindrome(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder half = new StringBuilder();
        char middleChar = 0;
        boolean hasMiddle = false;

        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);

            if (count[i] % 2 != 0) {
                middleChar = c;
                hasMiddle = true;
            }

            int half_count = count[i] / 2;
            for (int k = 0; k < half_count; k++) {
                half.append(c);
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(half);
        if (hasMiddle) {
            result.append(middleChar);
        }
        result.append(half.reverse());

        return result.toString();
    }
}