import java.util.*;

class Solution {
    public int findPairs(int[] nums, int k) {
        
        if (k == 0) {
            HashSet<Integer> seen = new HashSet<>();
            HashSet<Integer> duplicates = new HashSet<>();

            for (int num : nums) {
                if (seen.contains(num)) {
                    duplicates.add(num);
                } else {
                    seen.add(num);
                }
            }

            return duplicates.size();
        }

        HashSet<Integer> set = new HashSet<>();

        for (int num : nums) {
            set.add(num);
        }

        int count = 0;

        for (int num : set) {
            if (set.contains(num + k)) {
                count++;
            }
        }

        return count;
    }
}