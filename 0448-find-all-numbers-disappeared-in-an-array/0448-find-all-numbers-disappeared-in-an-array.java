class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int num : nums) {
            int idx = Math.abs(num) - 1;
            if (nums[idx] > 0) nums[idx] = -nums[idx];
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) result.add(i + 1);
        }

        return result;
    }
}