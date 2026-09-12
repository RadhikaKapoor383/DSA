import java.util.*;

class Solution {

    static class Interval {
        int l, r, w, idx;

        Interval(int l, int r, int w, int idx) {
            this.l = l;
            this.r = r;
            this.w = w;
            this.idx = idx;
        }
    }

    static class Node {
        long score;
        List<Integer> indices;

        Node(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.l != b.l)
                return Integer.compare(a.l, b.l);
            return Integer.compare(a.r, b.r);
        });

        int[] starts = new int[n];

        for (int i = 0; i < n; i++) {
            starts[i] = arr[i].l;
        }

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = upperBound(starts, arr[i].r);
        }

        Node[][] dp = new Node[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new Node(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 0; k <= 4; k++) {

                // Don't take this interval
                Node best = dp[i + 1][k];

                // Take this interval
                if (k > 0) {

                    Node nextNode = dp[next[i]][k - 1];

                    List<Integer> candidate =
                        new ArrayList<>(nextNode.indices);

                    candidate.add(arr[i].idx);

                    Collections.sort(candidate);

                    Node take = new Node(
                        arr[i].w + nextNode.score,
                        candidate
                    );

                    best = better(best, take);
                }

                dp[i][k] = best;
            }
        }

        // Convert List<Integer> to int[]
        List<Integer> result = dp[0][4].indices;

        int[] answer = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    private int upperBound(int[] arr, int target) {

        int left = 0;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] <= target)
                left = mid + 1;
            else
                right = mid;
        }

        return left;
    }

    private Node better(Node a, Node b) {

        if (a.score != b.score)
            return a.score > b.score ? a : b;

        return compare(a.indices, b.indices) <= 0 ? a : b;
    }

    private int compare(List<Integer> a, List<Integer> b) {

        int len = Math.min(a.size(), b.size());

        for (int i = 0; i < len; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}