class Solution {
    public int maximumPopulation(int[][] logs) {
        int[] delta = new int[2051]; // years 0..2050, we only use indices 1950-2050
        
        for (int[] log : logs) {
            delta[log[0]]++;
            delta[log[1]]--;
        }
        
        int maxPop = 0;
        int bestYear = 1950;
        int currentPop = 0;
        
        for (int year = 1950; year <= 2050; year++) {
            currentPop += delta[year];
            if (currentPop > maxPop) {
                maxPop = currentPop;
                bestYear = year;
            }
        }
        
        return bestYear;
    }
}