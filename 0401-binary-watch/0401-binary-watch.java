class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
        
        for (int config = 0; config < 1024; config++) {
            int hour = config >> 6;        // top 4 bits
            int minute = config & 0x3F;    // bottom 6 bits (mask with 111111)
            
            if (hour < 12 && minute < 60 && Integer.bitCount(config) == turnedOn) {
                result.add(String.format("%d:%02d", hour, minute));
            }
        }
        
        return result;
    }
}