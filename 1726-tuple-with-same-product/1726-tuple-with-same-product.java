import java.util.*;

class Solution {
    public int tupleSameProduct(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;

        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int product = nums[i] * nums[j];

                int count = map.getOrDefault(product, 0);
                ans += count * 8;

                map.put(product, count + 1);
            }
        }

        return ans;
    }
}