class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        List<Integer> result = new ArrayList<>();

        for (int num : nums1) set.add(num);

        for (int num : nums2) {
            if (set.contains(num)) {
                result.add(num);
                set.remove(num);
            }
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}