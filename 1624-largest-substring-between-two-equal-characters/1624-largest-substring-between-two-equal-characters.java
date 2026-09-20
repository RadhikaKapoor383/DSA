class Solution {
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] firstIndex = new int[26];
        Arrays.fill(firstIndex, -1);
        
        int answer = -1;
        
        for (int i = 0; i < s.length(); i++) {
            int charIdx = s.charAt(i) - 'a';
            
            if (firstIndex[charIdx] == -1) {
                firstIndex[charIdx] = i;
            } else {
                answer = Math.max(answer, i - firstIndex[charIdx] - 1);
            }
        }
        
        return answer;
    }
}