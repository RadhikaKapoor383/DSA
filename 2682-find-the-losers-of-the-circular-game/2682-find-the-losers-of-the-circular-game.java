class Solution {
    public int[] circularGameLosers(int n, int k) {
        boolean[] received = new boolean[n + 1];
        int pos = 1;
        int turn = 1;
        received[pos] = true;
        
        while (true) {
            int next = ((pos - 1 + turn * k) % n) + 1;
            if (received[next]) break;
            received[next] = true;
            pos = next;
            turn++;
        }
        
        List<Integer> losers = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!received[i]) losers.add(i);
        }
        
        int[] result = new int[losers.size()];
        for (int i = 0; i < result.length; i++) result[i] = losers.get(i);
        return result;
    }
}