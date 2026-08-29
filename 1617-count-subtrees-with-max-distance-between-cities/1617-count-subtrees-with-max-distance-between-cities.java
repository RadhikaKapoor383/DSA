class Solution {
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        
        int[] result = new int[n - 1];
        
        for (int mask = 1; mask < (1 << n); mask++) {
            int size = Integer.bitCount(mask);
            if (size < 2) continue;
            
            // find the first city in the subset (1-indexed)
            int start = -1;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    start = i + 1;
                    break;
                }
            }
            
            // BFS from start, restricted to cities in the mask
            int[] dist = bfs(adj, start, mask, n);
            
            // check connectivity: every city in mask must have been reached
            int reachedCount = 0;
            int maxDist = 0;
            int farthestCity = start;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    int city = i + 1;
                    if (dist[city] == -1) {
                        reachedCount = -1; // signal disconnection
                        break;
                    }
                    reachedCount++;
                    if (dist[city] > maxDist) {
                        maxDist = dist[city];
                        farthestCity = city;
                    }
                }
            }
            
            if (reachedCount != size) continue; // not connected, skip
            
            // BFS again from farthestCity to get the true diameter (standard tree diameter trick)
            int[] dist2 = bfs(adj, farthestCity, mask, n);
            int diameter = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    diameter = Math.max(diameter, dist2[i + 1]);
                }
            }
            
            result[diameter - 1]++;
        }
        
        return result;
    }
    
    private int[] bfs(List<List<Integer>> adj, int start, int mask, int n) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[start] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int next : adj.get(node)) {
                if ((mask & (1 << (next - 1))) != 0 && dist[next] == -1) {
                    dist[next] = dist[node] + 1;
                    queue.offer(next);
                }
            }
        }
        
        return dist;
    }
}