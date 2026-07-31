import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // Sort frequencies in descending order — most frequent letters get cheapest slots
        List<Integer> freqList = new ArrayList<>();
        for (int f : freq) {
            if (f > 0) {
                freqList.add(f);
            }
        }
        Collections.sort(freqList, Collections.reverseOrder());

        int totalPushes = 0;
        for (int i = 0; i < freqList.size(); i++) {
            int pushCost = (i / 8) + 1; // 1st 8 letters cost 1 push, next 8 cost 2, etc.
            totalPushes += pushCost * freqList.get(i);
        }

        return totalPushes;
    }
}