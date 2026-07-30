class Solution {
    public int minimumPushes(String word) {
        int n = word.length();
        int totalPushes = 0;

        // Since all letters are distinct (frequency 1 each), simply distribute
        // them evenly across the 8 available keys (2-9).
        // The i-th letter (0-indexed) assigned goes to push-count (i / 8) + 1.
        for (int i = 0; i < n; i++) {
            totalPushes += (i / 8) + 1;
        }

        return totalPushes;
    }
}