class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int count = 0;
        
        for (int a : arr1) {
            boolean hasCloseElement = false;
            for (int b : arr2) {
                if (Math.abs(a - b) <= d) {
                    hasCloseElement = true;
                    break;
                }
            }
            if (!hasCloseElement) {
                count++;
            }
        }
        
        return count;
    }
}