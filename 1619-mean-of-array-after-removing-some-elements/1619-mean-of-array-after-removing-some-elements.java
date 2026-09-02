class Solution {
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int removeCount = n / 20; // 5% from each end
        
        long sum = 0;
        for (int i = removeCount; i < n - removeCount; i++) {
            sum += arr[i];
        }
        
        int remaining = n - 2 * removeCount;
        return (double) sum / remaining;
    }
}