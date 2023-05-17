import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] input = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            input[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(input, (str1, str2) -> {
          int result1 = Integer.parseInt(str1 + str2);
          int result2 = Integer.parseInt(str2 + str1);

          return result2 - result1;
        });
        
        StringBuilder sb = new StringBuilder();
        for (String data : input) {
            sb.append(data);
        }
        return sb.toString().charAt(0) == '0' ? "0" : sb.toString();
    }
}