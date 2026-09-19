class Solution {
    public boolean checkOverlap(int radius, int xCenter, int yCenter, int x1, int y1, int x2, int y2) {
        long closestX = clamp(xCenter, x1, x2);
        long closestY = clamp(yCenter, y1, y2);
        
        long dx = xCenter - closestX;
        long dy = yCenter - closestY;
        
        return (dx * dx + dy * dy) <= (long) radius * radius;
    }
    
    private long clamp(long val, long min, long max) {
        if (val < min) return min;
        if (val > max) return max;
        return val;
    }
}