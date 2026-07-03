class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String doubled = s + s;
        String middle = doubled.substring(1, doubled.length() - 1);
        return middle.contains(s);
    }
}