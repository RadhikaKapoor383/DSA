class Solution {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) set1.add(num);
        
        Set<Integer> set2 = new HashSet<>();
        for (int num : nums2) set2.add(num);
        
        List<Integer> onlyInNums1 = new ArrayList<>();
        for (int num : set1) {
            if (!set2.contains(num)) {
                onlyInNums1.add(num);
            }
        }
        
        List<Integer> onlyInNums2 = new ArrayList<>();
        for (int num : set2) {
            if (!set1.contains(num)) {
                onlyInNums2.add(num);
            }
        }
        
        List<List<Integer>> result = new ArrayList<>();
        result.add(onlyInNums1);
        result.add(onlyInNums2);
        
        return result;
    }
}