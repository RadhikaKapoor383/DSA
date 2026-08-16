class Solution {
    public int countCharacters(String[] words, String chars) {
        int[] charsCount = new int[26];
        for (char c : chars.toCharArray()) {
            charsCount[c - 'a']++;
        }
        
        int totalLength = 0;
        
        for (String word : words) {
            int[] wordCount = new int[26];
            for (char c : word.toCharArray()) {
                wordCount[c - 'a']++;
            }
            
            if (isGood(wordCount, charsCount)) {
                totalLength += word.length();
            }
        }
        
        return totalLength;
    }
    
    private boolean isGood(int[] wordCount, int[] charsCount) {
        for (int i = 0; i < 26; i++) {
            if (wordCount[i] > charsCount[i]) {
                return false;
            }
        }
        return true;
    }
}