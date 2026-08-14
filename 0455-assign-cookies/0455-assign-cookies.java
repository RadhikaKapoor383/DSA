class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        
        int i = 0; // pointer for children (g)
        int j = 0; // pointer for cookies (s)
        int count = 0;
        
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                count++;
                i++; // this child is satisfied, move to next child
                j++; // this cookie is used, move to next cookie
            } else {
                j++; // cookie too small, try next bigger cookie
            }
        }
        
        return count;
    }
}