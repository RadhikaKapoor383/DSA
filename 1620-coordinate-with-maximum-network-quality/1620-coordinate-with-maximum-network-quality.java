class Solution {
    public int[] bestCoordinate(int[][] towers, int radius) {
        int bestQuality = -1;
        int bestX = 0, bestY = 0;
        
        for (int x = 0; x <= 50; x++) {
            for (int y = 0; y <= 50; y++) {
                int quality = 0;
                
                for (int[] tower : towers) {
                    int tx = tower[0], ty = tower[1], q = tower[2];
                    long distSq = (long)(tx - x) * (tx - x) + (long)(ty - y) * (ty - y);
                    
                    if (distSq <= (long) radius * radius) {
                        double d = Math.sqrt(distSq);
                        quality += (int) Math.floor(q / (1 + d));
                    }
                }
                
                if (quality > bestQuality) {
                    bestQuality = quality;
                    bestX = x;
                    bestY = y;
                }
            }
        }
        
        return new int[]{bestX, bestY};
    }
}