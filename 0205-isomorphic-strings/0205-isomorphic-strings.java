class Solution {
    public boolean isIsomorphic(String s, String t) {
        char[] map1 = new char[256];
        char[] map2 = new char[256];

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (map1[c1] == 0 && map2[c2] == 0) {
                map1[c1] = c2;
                map2[c2] = c1;
            } else if (map1[c1] != c2 || map2[c2] != c1) {
                return false;
            }
        }

        return true;
    }
}