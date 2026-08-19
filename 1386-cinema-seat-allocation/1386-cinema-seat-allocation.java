class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMask = new HashMap<>();
        
        for (int[] res : reservedSeats) {
            int row = res[0];
            int seat = res[1];
            int mask = rowMask.getOrDefault(row, 0);
            mask |= (1 << seat);
            rowMask.put(row, mask);
        }
        
        int blockA = (1 << 2) | (1 << 3) | (1 << 4) | (1 << 5); // seats 2,3,4,5
        int blockB = (1 << 4) | (1 << 5) | (1 << 6) | (1 << 7); // seats 4,5,6,7
        int blockC = (1 << 6) | (1 << 7) | (1 << 8) | (1 << 9); // seats 6,7,8,9
        
        int result = 0;
        
        for (int mask : rowMask.values()) {
            if ((mask & blockA) == 0 && (mask & blockC) == 0) {
                result += 2;
            } else if ((mask & blockA) == 0 || (mask & blockB) == 0 || (mask & blockC) == 0) {
                result += 1;
            }
        }
        
        long emptyRows = (long) n - rowMask.size();
        result += emptyRows * 2;
        
        return result;
    }
}