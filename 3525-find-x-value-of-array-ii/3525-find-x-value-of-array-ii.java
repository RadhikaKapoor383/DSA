class Solution {
    private int n, K;
    private int[] prod;
    private int[][] cnt;
    private int curS, ansAccum, queryX;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        n = nums.length;
        K = k;
        int size = 4 * Math.max(n, 1);
        prod = new int[size];
        cnt = new int[size][K * K];

        int[] modArr = new int[n];
        for (int i = 0; i < n; i++) modArr[i] = nums[i] % K;
        build(1, 0, n - 1, modArr);

        int q = queries.length;
        int[] ans = new int[q];
        for (int i = 0; i < q; i++) {
            int idx = queries[i][0];
            int val = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, idx, val % K);
            ans[i] = query(start, x);
        }
        return ans;
    }

    private void buildLeaf(int node, int val) {
        int v = val % K;
        prod[node] = v;
        int[] c = cnt[node];
        for (int s = 0; s < K; s++) {
            int res = (s * v) % K;
            for (int xx = 0; xx < K; xx++) {
                c[s * K + xx] = (xx == res) ? 1 : 0;
            }
        }
    }

    private void pull(int node) {
        int left = node * 2, right = node * 2 + 1;
        int[] c = cnt[node];
        int[] cl = cnt[left];
        int[] cr = cnt[right];
        int prodLeft = prod[left];

        for (int s = 0; s < K; s++) {
            int sMid = (s * prodLeft) % K;
            int base1 = s * K;
            int base2 = sMid * K;
            for (int x = 0; x < K; x++) {
                c[base1 + x] = cl[base1 + x] + cr[base2 + x];
            }
        }
        prod[node] = (prodLeft * prod[right]) % K;
    }

    private void build(int node, int l, int r, int[] modArr) {
        if (l == r) {
            buildLeaf(node, modArr[l]);
            return;
        }
        int mid = (l + r) / 2;
        build(node * 2, l, mid, modArr);
        build(node * 2 + 1, mid + 1, r, modArr);
        pull(node);
    }

    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            buildLeaf(node, val);
            return;
        }
        int mid = (l + r) / 2;
        if (idx <= mid) update(node * 2, l, mid, idx, val);
        else update(node * 2 + 1, mid + 1, r, idx, val);
        pull(node);
    }

    private void querySuffix(int node, int l, int r, int start) {
        if (r < start) return; // pura node range se bahar (start se pehle)
        if (l >= start) {
            // pura node range ke andar
            ansAccum += cnt[node][curS * K + queryX];
            curS = (curS * prod[node]) % K;
            return;
        }
        int mid = (l + r) / 2;
        querySuffix(node * 2, l, mid, start);
        querySuffix(node * 2 + 1, mid + 1, r, start);
    }

    private int query(int start, int x) {
        curS = 1 % K;
        ansAccum = 0;
        queryX = x;
        querySuffix(1, 0, n - 1, start);
        return ansAccum;
    }
}