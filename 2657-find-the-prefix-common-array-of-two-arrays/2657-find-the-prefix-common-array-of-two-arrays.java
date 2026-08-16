class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] freq = new int[n + 1];
        int[] result = new int[n];
        int commonCount = 0;
        
        for (int i = 0; i < n; i++) {
            int a = A[i];
            int b = B[i];
            
            freq[a]++;
            if (freq[a] == 2) commonCount++;
            
            freq[b]++;
            if (freq[b] == 2) commonCount++;
            
            result[i] = commonCount;
        }
        
        return result;
    }
}