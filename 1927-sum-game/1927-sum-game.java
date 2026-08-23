class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;
        
        long sum1 = 0, sum2 = 0;
        int cnt1 = 0, cnt2 = 0;
        
        for (int i = 0; i < half; i++) {
            char c = num.charAt(i);
            if (c == '?') cnt1++;
            else sum1 += (c - '0');
        }
        for (int i = half; i < n; i++) {
            char c = num.charAt(i);
            if (c == '?') cnt2++;
            else sum2 += (c - '0');
        }
        
        int cnt = cnt1 + cnt2;
        if (cnt % 2 != 0) {
            return true; // Alice always wins
        }
        
        long diff = sum1 - sum2;
        long V = diff + 9L * (cnt1 - cnt2) / 2;
        
        return V != 0; // Alice wins iff Bob cannot force equality
    }
}