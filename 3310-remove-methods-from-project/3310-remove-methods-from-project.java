import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Build adjacency list: a -> list of b (a invokes b)
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            adj.get(inv[0]).add(inv[1]);
        }

        // BFS from k to find all suspicious methods (k + everything reachable from k)
        boolean[] suspicious = new boolean[n];
        suspicious[k] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(k);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int next : adj.get(current)) {
                if (!suspicious[next]) {
                    suspicious[next] = true;
                    queue.offer(next);
                }
            }
        }

        // Check if any non-suspicious method invokes a suspicious method
        boolean canRemove = true;
        for (int[] inv : invocations) {
            int a = inv[0];
            int b = inv[1];
            if (suspicious[b] && !suspicious[a]) {
                canRemove = false;
                break;
            }
        }

        List<Integer> result = new ArrayList<>();

        // If we can't remove, return all methods unchanged
        if (!canRemove) {
            for (int i = 0; i < n; i++) {
                result.add(i);
            }
            return result;
        }

        // Otherwise, return all methods NOT in the suspicious set
        for (int i = 0; i < n; i++) {
            if (!suspicious[i]) {
                result.add(i);
            }
        }
        return result;
    }
}