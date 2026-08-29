class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[][] indexed = new int[n][2]; // {value, originalIndex}
        for (int i = 0; i < n; i++) {
            indexed[i][0] = nums[i];
            indexed[i][1] = i;
        }
        
        Arrays.sort(indexed, (a, b) -> a[0] - b[0]);
        
        int[] result = new int[n];
        int i = 0;
        
        while (i < n) {
            int j = i;
            List<Integer> originalIndices = new ArrayList<>();
            originalIndices.add(indexed[i][1]);
            
            while (j + 1 < n && indexed[j + 1][0] - indexed[j][0] <= limit) {
                j++;
                originalIndices.add(indexed[j][1]);
            }
            
            // originalIndices currently correspond 1:1 with sorted values indexed[i..j][0]
            // sort the original indices ascending, then assign sorted values in order
            Collections.sort(originalIndices);
            
            for (int k = 0; k < originalIndices.size(); k++) {
                result[originalIndices.get(k)] = indexed[i + k][0];
            }
            
            i = j + 1;
        }
        
        return result;
    }
}