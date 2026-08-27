class Solution {
    public String lexGreaterPermutation(String s, String target) {
        
        int n = s.length();
        int[] counts = new int[26];
        for (char c : s.toCharArray()) {
            counts[c - 'a']++;
        }
        
        int lastGoodPos = -1;
        int[] lastGoodCounts = null;
        
        for (int i = 0; i < n; i++) {
            char c = target.charAt(i);
            
            // check feasibility of deviating here: is there a char > c available?
            boolean feasible = false;
            for (int x = c - 'a' + 1; x < 26; x++) {
                if (counts[x] > 0) {
                    feasible = true;
                    break;
                }
            }
            if (feasible) {
                lastGoodPos = i;
                lastGoodCounts = counts.clone();
            }
            
            // try to match target[i] exactly
            if (counts[c - 'a'] > 0) {
                counts[c - 'a']--;
            } else {
                break; // can't continue tight match
            }
        }
        
        if (lastGoodPos == -1) {
            return "";
        }
        
        StringBuilder result = new StringBuilder();
        result.append(target, 0, lastGoodPos);
        
        char targetChar = target.charAt(lastGoodPos);
        int chosen = -1;
        for (int x = targetChar - 'a' + 1; x < 26; x++) {
            if (lastGoodCounts[x] > 0) {
                chosen = x;
                break;
            }
        }
        lastGoodCounts[chosen]--;
        result.append((char) ('a' + chosen));
        
        for (int x = 0; x < 26; x++) {
            for (int k = 0; k < lastGoodCounts[x]; k++) {
                result.append((char) ('a' + x));
            }
        }
        
        return result.toString();
    }
}