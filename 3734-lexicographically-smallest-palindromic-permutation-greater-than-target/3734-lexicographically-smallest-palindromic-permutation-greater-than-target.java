class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) count[c - 'a']++;
        
        boolean isOdd = (n % 2 == 1);
        int oddCount = 0, oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) { oddCount++; oddChar = i; }
        }
        if (isOdd && oddCount != 1) return "";
        if (!isOdd && oddCount != 0) return "";
        
        int[] halfCounts = new int[26];
        for (int i = 0; i < 26; i++) halfCounts[i] = count[i] / 2;
        
        int freeLen = n / 2;
        int[] working = halfCounts.clone();
        int lastDevPos = -1;
        int[] lastDevSnap = null;
        boolean tightBroke = false;
        
        for (int i = 0; i < freeLen; i++) {
            int c = target.charAt(i) - 'a';
            boolean feasible = false;
            for (int x = c + 1; x < 26; x++) {
                if (working[x] > 0) { feasible = true; break; }
            }
            if (feasible) {
                lastDevPos = i;
                lastDevSnap = working.clone();
            }
            if (working[c] > 0) {
                working[c]--;
            } else {
                tightBroke = true;
                break;
            }
        }
        
        if (!tightBroke) {
            String freePartTight = target.substring(0, freeLen);
            String reversedFree = new StringBuilder(freePartTight).reverse().toString();
            
            if (isOdd) {
                int midIdx = freeLen;
                char tCh = target.charAt(midIdx);
                if ((char)('a' + oddChar) > tCh) {
                    return freePartTight + (char)('a' + oddChar) + reversedFree;
                } else if ((char)('a' + oddChar) == tCh) {
                    String targetSuffix = target.substring(midIdx + 1);
                    if (reversedFree.compareTo(targetSuffix) > 0) {
                        return freePartTight + (char)('a' + oddChar) + reversedFree;
                    }
                }
            } else {
                String targetSuffix = target.substring(freeLen);
                if (reversedFree.compareTo(targetSuffix) > 0) {
                    return freePartTight + reversedFree;
                }
            }
        }
        
        if (lastDevPos == -1) return "";
        
        int[] snap = lastDevSnap;
        int p = lastDevPos;
        StringBuilder freePart = new StringBuilder();
        freePart.append(target, 0, p);
        
        int tChar = target.charAt(p) - 'a';
        int chosen = -1;
        for (int x = tChar + 1; x < 26; x++) {
            if (snap[x] > 0) { chosen = x; break; }
        }
        snap[chosen]--;
        freePart.append((char)('a' + chosen));
        
        for (int x = 0; x < 26; x++) {
            for (int k = 0; k < snap[x]; k++) freePart.append((char)('a' + x));
        }
        
        String freePartStr = freePart.toString();
        String reversedFree = new StringBuilder(freePartStr).reverse().toString();
        
        StringBuilder result = new StringBuilder();
        result.append(freePartStr);
        if (isOdd) result.append((char)('a' + oddChar));
        result.append(reversedFree);
        
        return result.toString();
    }
}