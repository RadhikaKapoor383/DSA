class Solution {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int k = queryCharacters.length();
        int[] result = new int[k];
        
        TreeMap<Integer, Integer> runs = new TreeMap<>();      // start -> length
        TreeMap<Integer, Integer> lenCount = new TreeMap<>();  // length -> count
        
        // Build initial runs
        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && arr[j] == arr[i]) j++;
            int length = j - i;
            runs.put(i, length);
            lenCount.merge(length, 1, Integer::sum);
            i = j;
        }
        
        for (int q = 0; q < k; q++) {
            int idx = queryIndices[q];
            char ch = queryCharacters.charAt(q);
            
            if (arr[idx] != ch) {
                int st = runs.floorKey(idx);
                int len = runs.get(st);
                int end = st + len;
                
                removeRun(runs, lenCount, st);
                
                if (idx > st) {
                    addRun(runs, lenCount, st, idx - st);
                }
                if (end > idx + 1) {
                    addRun(runs, lenCount, idx + 1, end - idx - 1);
                }
                
                arr[idx] = ch;
                
                int newStart = idx;
                int newLen = 1;
                
                if (idx > 0 && arr[idx - 1] == ch) {
                    int leftStart = runs.floorKey(idx - 1);
                    int leftLen = runs.get(leftStart);
                    removeRun(runs, lenCount, leftStart);
                    newStart = leftStart;
                    newLen += leftLen;
                }
                
                if (idx + 1 < n && arr[idx + 1] == ch) {
                    int rightStart = idx + 1;
                    int rightLen = runs.get(rightStart);
                    removeRun(runs, lenCount, rightStart);
                    newLen += rightLen;
                }
                
                addRun(runs, lenCount, newStart, newLen);
            }
            
            result[q] = lenCount.lastKey();
        }
        
        return result;
    }
    
    private void addRun(TreeMap<Integer,Integer> runs, TreeMap<Integer,Integer> lenCount, int start, int length) {
        runs.put(start, length);
        lenCount.merge(length, 1, Integer::sum);
    }
    
    private void removeRun(TreeMap<Integer,Integer> runs, TreeMap<Integer,Integer> lenCount, int start) {
        Integer length = runs.remove(start);
        if (length != null) {
            int c = lenCount.get(length);
            if (c == 1) lenCount.remove(length);
            else lenCount.put(length, c - 1);
        }
    }
}