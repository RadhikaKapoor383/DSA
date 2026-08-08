class Solution {
    public boolean checkIfCanBreak(String s1, String s2) {
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();
        Arrays.sort(a);
        Arrays.sort(b);
        
        int n = a.length;
        boolean s1BreaksS2 = true, s2BreaksS1 = true;
        
        for (int i = 0; i < n; i++) {
            if (a[i] < b[i]) s1BreaksS2 = false;
            if (b[i] < a[i]) s2BreaksS1 = false;
        }
        
        return s1BreaksS2 || s2BreaksS1;
    }
}