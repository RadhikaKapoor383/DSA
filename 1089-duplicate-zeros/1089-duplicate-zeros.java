class Solution {
    public void duplicateZeros(int[] arr) {
        int n = arr.length;
        int countZeros = 0;
        
        // Count all zeros in the array
        for (int num : arr) {
            if (num == 0) countZeros++;
        }
        
        int i = n - 1;               // pointer into original array (reading)
        int j = n + countZeros - 1;  // pointer into "virtual" expanded array (writing)
        
        while (i >= 0) {
            if (j < n) {
                arr[j] = arr[i];
            }
            j--;
            
            if (arr[i] == 0) {
                if (j < n) {
                    arr[j] = 0;
                }
                j--;
            }
            i--;
        }
    }
}