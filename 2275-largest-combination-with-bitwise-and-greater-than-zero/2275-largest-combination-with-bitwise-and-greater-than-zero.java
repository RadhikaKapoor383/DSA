class Solution {
    public int largestCombination(int[] candidates) {
        int maxCount = 0;

        // For each bit position (0 to 23, since candidates[i] <= 10^7 < 2^24),
        // count how many numbers have that bit set.
        for (int bit = 0; bit < 24; bit++) {
            int count = 0;
            for (int num : candidates) {
                if ((num & (1 << bit)) != 0) {
                    count++;
                }
            }
            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }
}