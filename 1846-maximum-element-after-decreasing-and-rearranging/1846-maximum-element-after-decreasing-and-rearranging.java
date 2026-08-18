class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        
        arr[0] = 1; // first element must be exactly 1 (and it's a positive integer, so this is always valid/optimal)
        
        for (int i = 1; i < arr.length; i++) {
            arr[i] = Math.min(arr[i], arr[i - 1] + 1);
        }
        
        return arr[arr.length - 1];
    }
}