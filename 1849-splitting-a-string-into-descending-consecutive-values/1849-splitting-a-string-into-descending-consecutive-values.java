class Solution {
    public boolean splitString(String s) {
        int n = s.length();
        
        for (int i = 1; i < n; i++) { // first substring is s[0, i), must leave room for at least one more part
            String firstStr = s.substring(0, i);
            java.math.BigInteger firstVal = new java.math.BigInteger(firstStr);
            
            if (backtrack(s, i, firstVal)) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean backtrack(String s, int pos, java.math.BigInteger prevValue) {
        if (pos == s.length()) {
            return true; // successfully consumed the whole string
        }
        
        java.math.BigInteger target = prevValue.subtract(java.math.BigInteger.ONE);
        if (target.signum() < 0) {
            return false; // can't have a negative value from digit-only substrings
        }
        
        for (int len = 1; pos + len <= s.length(); len++) {
            String sub = s.substring(pos, pos + len);
            java.math.BigInteger value = new java.math.BigInteger(sub);
            
            int cmp = value.compareTo(target);
            if (cmp == 0) {
                if (backtrack(s, pos + len, target)) {
                    return true;
                }
            } else if (cmp > 0) {
                break; // value only grows from here, no point continuing
            }
        }
        
        return false;
    }
}