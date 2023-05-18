import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int test = 0; test < T; test++) {
            TreeMap<Integer, Integer> numbers = new TreeMap<>();
            int k = Integer.parseInt(br.readLine());
            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int number = Integer.parseInt(st.nextToken());

                if (command.equals("I")) {
                    // number 큐에 삽입
                    numbers.put(number, numbers.getOrDefault(number, 0) + 1);
                } else if (command.equals("D")) {
                    if (number == 1 && !numbers.isEmpty()) {
                        // 최댓값을 제거, 동일한 값이 2개 있어도 하나만 제거
                        int max = numbers.lastKey();
                        if (numbers.get(max) == 1) {
                            numbers.remove(max);
                        } else {
                            numbers.put(max, numbers.get(max) - 1);
                        }
                    } else if (number == -1 && !numbers.isEmpty()) {
                        // 최솟값을 제거, 동일한 값이 2개 하나만 제거
                        int min = numbers.firstKey();
                        if (numbers.get(min) == 1) {
                            numbers.remove(min);
                        } else {
                            numbers.put(min, numbers.get(min) - 1);
                        }
                    }
                }
            }

            if (numbers.isEmpty()) { // 큐가 비어있다면
                sb.append("EMPTY").append("\n");
            } else {
                sb.append(numbers.lastKey()).append(" ").append(numbers.firstKey()).append("\n");
            }
        }
        System.out.println(sb);
    }
}
