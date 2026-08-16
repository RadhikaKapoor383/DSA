class Solution {
    public int findKthPositive(int[] arr, int k) {
        for (int i = 0; i < arr.length; i++) {
            int missingBefore = arr[i] - (i + 1);
            if (missingBefore >= k) {
                // the answer lies before arr[i]; back-calculate it
                return i + k;
            }
        }
        // k exceeds all missing numbers within the array's range;
        // the answer is beyond the last element
        return arr.length + k;
    }
}