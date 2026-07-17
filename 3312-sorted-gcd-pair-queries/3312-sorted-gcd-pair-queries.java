class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int x : nums) maxVal = Math.max(maxVal, x);

        // Step 1: frequency count
        int[] freq = new int[maxVal + 1];
        for (int x : nums) freq[x]++;

        // Step 2: multiple[d] = kitne numbers d se divisible hain
        long[] multiple = new long[maxVal + 1];
        for (int d = 1; d <= maxVal; d++) {
            long sum = 0;
            for (int v = d; v <= maxVal; v += d) {
                sum += freq[v];
            }
            multiple[d] = sum;
        }

        // Step 3 & 4: exact[d] = pairs jinka GCD exactly d hai (inclusion-exclusion)
        long[] exact = new long[maxVal + 1];
        for (int d = maxVal; d >= 1; d--) {
            long total = multiple[d] * (multiple[d] - 1) / 2;
            for (int k = 2 * d; k <= maxVal; k += d) {
                total -= exact[k];
            }
            exact[d] = total;
        }

        // Step 5: prefix sum banao
        long[] prefix = new long[maxVal + 1];
        for (int d = 1; d <= maxVal; d++) {
            prefix[d] = prefix[d - 1] + exact[d];
        }

        // Step 6: har query ke liye binary search
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long q = queries[i];  // ab yeh already long hai, direct assign
            int lo = 1, hi = maxVal, result = maxVal;
            while (lo <= hi) {
                int mid = (lo + hi) / 2;
                if (prefix[mid] > q) {
                    result = mid;
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
            answer[i] = result;
        }

        return answer;
    }
}