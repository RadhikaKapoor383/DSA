class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }

        int m = num1.length(), n = num2.length();
        int[] result = new int[m + n];

        for (int i = m - 1; i >= 0; i--) {
            int digit1 = num1.charAt(i) - '0';

            for (int j = n - 1; j >= 0; j--) {
                int digit2 = num2.charAt(j) - '0';

                int product = digit1 * digit2;

                int lowPos = i + j + 1;
                int highPos = i + j;

                int sum = product + result[lowPos];

                result[lowPos] = sum % 10;
                result[highPos] += sum / 10;
            }
        }

        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < result.length && result[idx] == 0) {
            idx++;
        }
        for (; idx < result.length; idx++) {
            sb.append(result[idx]);
        }

        return sb.toString();
    }
}