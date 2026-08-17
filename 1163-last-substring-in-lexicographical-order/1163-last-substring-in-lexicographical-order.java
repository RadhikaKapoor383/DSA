class Solution {
    public String lastSubstring(String s) {
        int n = s.length();
        int i = 0, j = 1, k = 0;
        
        while (j + k < n) {
            char ci = s.charAt(i + k);
            char cj = s.charAt(j + k);
            
            if (ci == cj) {
                k++;
            } else if (ci < cj) {
                // suffix starting at i is worse; jump i past the matched region
                i = Math.max(i + k + 1, j);
                j = i + 1;
                k = 0;
            } else { // ci > cj
                // suffix starting at j is worse; try next candidate after j
                j = j + k + 1;
                k = 0;
            }
        }
        
        return s.substring(i);
    }
}