import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

class Solution {
    public long[] getDistances(int[] arr) {
        int n = arr.length;
        long[] result = new long[n];

        // Group indices by their value
        Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
        for (int i = 0; i < n; i++) {
            valueToIndices.computeIfAbsent(arr[i], k -> new ArrayList<>()).add(i);
        }

        // Process each group of same-valued indices
        for (List<Integer> indices : valueToIndices.values()) {
            int m = indices.size();

            // total = sum of all indices in this group
            long total = 0;
            for (int idx : indices) {
                total += idx;
            }

            long leftSum = 0; // sum of indices before current position in the group

            for (int k = 0; k < m; k++) {
                long idx = indices.get(k);
                long rightSum = total - leftSum - idx; // sum of indices after current position

                // Contribution from elements to the left: idx*k - leftSum
                // Contribution from elements to the right: rightSum - idx*(m-k-1)
                long leftContribution = idx * k - leftSum;
                long rightContribution = rightSum - idx * (m - k - 1);

                result[(int) idx] = leftContribution + rightContribution;

                leftSum += idx;
            }
        }

        return result;
    }
}