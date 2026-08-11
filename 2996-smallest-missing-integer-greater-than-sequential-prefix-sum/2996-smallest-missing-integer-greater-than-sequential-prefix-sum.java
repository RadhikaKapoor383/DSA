class Solution {
    public int missingInteger(int[] nums) {
        // Step 1: find longest sequential prefix and its sum
        int sum = nums[0];
        int i = 1;
        while (i < nums.length && nums[i] == nums[i - 1] + 1) {
            sum += nums[i];
            i++;
        }
        
        // Step 2: put all nums into a set for O(1) lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        
        // Step 3: find smallest x >= sum not in the array
        while (set.contains(sum)) {
            sum++;
        }
        
        return sum;
    }
}