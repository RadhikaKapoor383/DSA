class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int n = intervals.length;
        int q = queries.length;
        
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        Integer[] queryOrder = new Integer[q];
        for (int i = 0; i < q; i++) queryOrder[i] = i;
        Arrays.sort(queryOrder, (a, b) -> queries[a] - queries[b]);
        
        // min-heap ordered by interval size, storing {size, right}
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        int[] result = new int[q];
        int intervalPtr = 0;
        
        for (int qi : queryOrder) {
            int query = queries[qi];
            
            // add all intervals with left <= query
            while (intervalPtr < n && intervals[intervalPtr][0] <= query) {
                int left = intervals[intervalPtr][0];
                int right = intervals[intervalPtr][1];
                int size = right - left + 1;
                heap.offer(new int[]{size, right});
                intervalPtr++;
            }
            
            // remove expired intervals (right < query) from the top
            while (!heap.isEmpty() && heap.peek()[1] < query) {
                heap.poll();
            }
            
            result[qi] = heap.isEmpty() ? -1 : heap.peek()[0];
        }
        
        return result;
    }
}