class Solution {
    public int getMinSwaps(String num, int k) {
        char[] original = num.toCharArray();
        char[] target = num.toCharArray();
        
        for (int i = 0; i < k; i++) {
            nextPermutation(target);
        }
        
        return minAdjacentSwaps(original, target);
    }
    
    private void nextPermutation(char[] arr) {
        int n = arr.length;
        int i = n - 2;
        
        // Find the rightmost index where arr[i] < arr[i+1]
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        
        if (i >= 0) {
            // Find the rightmost element greater than arr[i] to swap with
            int j = n - 1;
            while (arr[j] <= arr[i]) {
                j--;
            }
            swap(arr, i, j);
        }
        
        // Reverse the suffix after index i to get the smallest arrangement
        reverse(arr, i + 1, n - 1);
    }
    
    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    private void reverse(char[] arr, int left, int right) {
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }
    
    private int minAdjacentSwaps(char[] original, char[] target) {
        int n = original.length;
        List<Character> remaining = new ArrayList<>();
        for (char c : original) {
            remaining.add(c);
        }
        
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            char targetChar = target[i];
            int idx = 0;
            while (remaining.get(idx) != targetChar) {
                idx++;
            }
            swaps += idx; // number of elements to hop over to bring this digit to the front
            remaining.remove(idx);
        }
        
        return swaps;
    }
}