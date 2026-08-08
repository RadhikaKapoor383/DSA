class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();
        
        // suffix[i] = length of longest suffix of word2 starting at some index j
        // that can be matched (exactly) as a subsequence in word1[i..n-1]
        // We compute canMatch[i][j] style via suffix DP: 
        // maxMatch[i] = the maximum number of characters of word2 (starting from position j going backwards)
        // Instead, compute: suf[i] = the maximum length k such that word2's suffix of length k 
        // (i.e., word2[m-k..m-1]) is a subsequence of word1[i..n-1] (exact match, no changes)
        
        int[] suf = new int[n + 2];
        suf[n] = 0;
        int j = m - 1;
        int[] sufAt = new int[n + 1]; // sufAt[i] = max chars of word2 (from the end) matchable in word1[i:]
        sufAt[n] = 0;
        for (int i = n - 1; i >= 0; i--) {
            sufAt[i] = sufAt[i + 1];
            int matched = m - sufAt[i]; // next index in word2 (from end) we need is m - sufAt[i] - 1
            int need = m - sufAt[i] - 1;
            if (need >= 0 && word1.charAt(i) == word2.charAt(need)) {
                sufAt[i] = sufAt[i] + 1;
            }
        }
        // sufAt[i] tells: max number of trailing characters of word2 that can be matched as subsequence using word1[i..n-1]
        // so the number of word2 chars matchable from word1[i:] (suffix) is sufAt[i], meaning word2[m-sufAt[i]..m-1] is subsequence of word1[i:]
        
        int[] result = new int[m];
        int i = 0; // pointer in word1
        int k = 0; // pointer in word2
        boolean usedChange = false;
        int resIdx = 0;
        
        while (k < m) {
            if (i >= n) return new int[0];
            if (word1.charAt(i) == word2.charAt(k)) {
                result[resIdx++] = i;
                i++;
                k++;
            } else {
                if (!usedChange) {
                    // try to use a change here: pick this index i for word2[k], mark as changed
                    // check if remaining word2[k+1..] can be matched (exactly) as subsequence in word1[i+1..]
                    int remainingNeeded = m - (k + 1); // characters left after this one
                    if (remainingNeeded == 0 || sufAt[i + 1] >= remainingNeeded) {
                        result[resIdx++] = i;
                        i++;
                        k++;
                        usedChange = true;
                    } else {
                        i++;
                    }
                } else {
                    i++;
                }
            }
        }
        
        if (k < m) return new int[0];
        return result;
    }
}