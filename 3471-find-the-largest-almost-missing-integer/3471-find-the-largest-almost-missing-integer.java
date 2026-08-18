class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> windowCount = new HashMap<>();
        
        for (int s = 0; s <= n - k; s++) {
            Set<Integer> distinctInWindow = new HashSet<>();
            for (int i = s; i < s + k; i++) {
                distinctInWindow.add(nums[i]);
            }
            for (int v : distinctInWindow) {
                windowCount.merge(v, 1, Integer::sum);
            }
        }
        
        int result = -1;
        for (Map.Entry<Integer, Integer> entry : windowCount.entrySet()) {
            if (entry.getValue() == 1) {
                result = Math.max(result, entry.getKey());
            }
        }
        
        return result;
    }
}