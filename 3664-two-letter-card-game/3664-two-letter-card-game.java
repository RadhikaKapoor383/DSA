class Solution {
    public int score(String[] cards, char x) {
         int[] countA = new int[26]; 
        int[] countB = new int[26]; 
        int countX = 0;

        for (String card : cards) {
            char c0 = card.charAt(0);
            char c1 = card.charAt(1);
            if (c0 == x && c1 == x) {
                countX++;
            } else if (c0 == x) {
                countA[c1 - 'a']++;
            } else if (c1 == x) {
                countB[c0 - 'a']++;
            }
        }

        int SA = 0, maxA = 0;
        for (int c : countA) { SA += c; maxA = Math.max(maxA, c); }
        int SB = 0, maxB = 0;
        for (int c : countB) { SB += c; maxB = Math.max(maxB, c); }

        int best = 0;
        for (int tA = 0; tA <= countX; tA++) {
            int tB = countX - tA;
            int fA = matchCount(SA, maxA, tA);
            int fB = matchCount(SB, maxB, tB);
            best = Math.max(best, fA + fB);
        }
        return best;
    }

    private int matchCount(int S, int m, int t) {
        int total = S + t;
        int maxClass = Math.max(m, t);
        return Math.min(total / 2, total - maxClass);
    }
}