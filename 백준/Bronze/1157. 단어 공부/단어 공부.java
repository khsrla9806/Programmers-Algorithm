import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int maxCnt = Integer.MIN_VALUE;
        Map<Character, Integer> map = new HashMap<>();
        char[] data = reader.readLine().toUpperCase().toCharArray();

        for (int i = 0; i < data.length; i++) {
            int beforeDataCnt = map.getOrDefault(data[i], 0);
            map.put(data[i], beforeDataCnt + 1);
            maxCnt = Math.max(maxCnt, beforeDataCnt + 1);
        }

        char answer = '?';
        int answerCnt = 0;
        for (char ch : map.keySet()) {
            if (map.get(ch) == maxCnt) {
                if (answerCnt > 0) {
                    answer = '?';
                    break;
                }
                answer = ch;
                answerCnt++;
            }
        }

        System.out.println(answer);
    }
}