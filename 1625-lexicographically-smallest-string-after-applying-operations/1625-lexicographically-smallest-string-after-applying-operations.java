class Solution {
    public String findLexSmallestString(String s, int a, int b) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        
        queue.offer(s);
        visited.add(s);
        String minStr = s;
        
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            
            if (cur.compareTo(minStr) < 0) {
                minStr = cur;
            }
            
            // Operation 1: Add 'a' to all odd indices
            String added = applyAdd(cur, a);
            if (!visited.contains(added)) {
                visited.add(added);
                queue.offer(added);
            }
            
            // Operation 2: Rotate right by 'b'
            String rotated = applyRotate(cur, b);
            if (!visited.contains(rotated)) {
                visited.add(rotated);
                queue.offer(rotated);
            }
        }
        
        return minStr;
    }
    
    private String applyAdd(String s, int a) {
        char[] arr = s.toCharArray();
        for (int i = 1; i < arr.length; i += 2) {
            int digit = (arr[i] - '0' + a) % 10;
            arr[i] = (char) ('0' + digit);
        }
        return new String(arr);
    }
    
    private String applyRotate(String s, int b) {
        int n = s.length();
        b = b % n;
        // right rotate by b: last b characters aage aa jayenge
        return s.substring(n - b) + s.substring(0, n - b);
    }
}