class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String best = "";
        int bestLen = Integer.MAX_VALUE;
        
        int left = 0;
        int onesCount = 0;
        
        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                onesCount++;
            }
            
            // shrink from left while we have more than k ones, or while shrinking still keeps exactly k ones
            while (onesCount > k || (onesCount == k && s.charAt(left) == '0')) {
                if (s.charAt(left) == '1') {
                    onesCount--;
                }
                left++;
            }
            
            if (onesCount == k) {
                int len = right - left + 1;
                String candidate = s.substring(left, right + 1);
                
                if (len < bestLen || (len == bestLen && candidate.compareTo(best) < 0)) {
                    bestLen = len;
                    best = candidate;
                }
            }
        }
        
        return best;
    }
}