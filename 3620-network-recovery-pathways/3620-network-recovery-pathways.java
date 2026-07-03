class Solution {
    List<int[]>[] graph;
    int n;
    boolean[] online;

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        this.online = online;
        n = online.length;

        graph = new ArrayList[n];
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        int[] indegree = new int[n];
        int hi = 0;

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            hi = Math.max(hi, e[2]);
        }

        int[] topo = new int[n];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                q.offer(i);

        int idx = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;

            for (int[] e : graph[u]) {
                if (--indegree[e[0]] == 0)
                    q.offer(e[0]);
            }
        }

        int lo = 0, ans = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (check(mid, topo, k)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean check(int limit, int[] topo, long k) {
        long INF = Long.MAX_VALUE / 4;
        long[] dist = new long[n];
        Arrays.fill(dist, INF);
        dist[0] = 0;

        for (int u : topo) {
            if (dist[u] == INF)
                continue;

            if (u != 0 && u != n - 1 && !online[u])
                continue;

            for (int[] edge : graph[u]) {
                int v = edge[0];
                int cost = edge[1];

                if (cost < limit)
                    continue;

                if (v != n - 1 && !online[v])
                    continue;

                if (dist[u] + cost < dist[v]) {
                    dist[v] = dist[u] + cost;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}