class Solution {
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        int n = rooms.length;
        int k = queries.length;
        
        // Sort rooms by size descending
        Arrays.sort(rooms, (a, b) -> b[1] - a[1]);
        
        // Sort query indices by minSize descending
        Integer[] queryOrder = new Integer[k];
        for (int i = 0; i < k; i++) queryOrder[i] = i;
        Arrays.sort(queryOrder, (a, b) -> queries[b][1] - queries[a][1]);
        
        TreeSet<Integer> availableIds = new TreeSet<>();
        int[] result = new int[k];
        int roomPtr = 0;
        
        for (int qi : queryOrder) {
            int preferred = queries[qi][0];
            int minSize = queries[qi][1];
            
            // Add all rooms with size >= minSize into the available set
            while (roomPtr < n && rooms[roomPtr][1] >= minSize) {
                availableIds.add(rooms[roomPtr][0]);
                roomPtr++;
            }
            
            if (availableIds.isEmpty()) {
                result[qi] = -1;
                continue;
            }
            
            Integer floorId = availableIds.floor(preferred);
            Integer ceilId = availableIds.ceiling(preferred);
            
            if (floorId == null) {
                result[qi] = ceilId;
            } else if (ceilId == null) {
                result[qi] = floorId;
            } else {
                // tie-break: prefer smaller id, i.e. prefer floor if distances equal
                int distFloor = preferred - floorId;
                int distCeil = ceilId - preferred;
                result[qi] = (distFloor <= distCeil) ? floorId : ceilId;
            }
        }
        
        return result;
    }
}