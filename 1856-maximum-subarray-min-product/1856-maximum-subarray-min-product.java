import java.util.*;

class Solution {
    public int maxSumMinProduct(int[] nums) {

        int n = nums.length;

        // Prefix sum
        long[] prefix = new long[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        long maxProduct = 0;

        // Monotonic increasing stack
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i <= n; i++) {

            long current = (i == n) ? 0 : nums[i];

            while (!stack.isEmpty() &&
                   nums[stack.peek()] > current) {

                int mid = stack.pop();

                int left = stack.isEmpty()
                        ? 0
                        : stack.peek() + 1;

                int right = i - 1;

                long sum = prefix[right + 1] - prefix[left];

                long product = (long) nums[mid] * sum;

                maxProduct = Math.max(maxProduct, product);
            }

            stack.push(i);
        }

        return (int) (maxProduct % 1_000_000_007);
    }
}