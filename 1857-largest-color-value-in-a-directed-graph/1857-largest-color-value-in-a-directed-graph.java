class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        
        int[] indegree = new int[n];
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            indegree[e[1]]++;
        }
        
        int[][] count = new int[n][26]; // count[node][c] = max nodes of color c along any path ending at node
        
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int processed = 0;
        int result = 0;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            processed++;
            
            // include this node's own color
            count[node][colors.charAt(node) - 'a']++;
            
            result = Math.max(result, count[node][colors.charAt(node) - 'a']);
            
            for (int next : adj.get(node)) {
                for (int c = 0; c < 26; c++) {
                    count[next][c] = Math.max(count[next][c], count[node][c]);
                }
                
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }
        
        if (processed != n) {
            return -1; // cycle detected
        }
        
        return result;
    }
}