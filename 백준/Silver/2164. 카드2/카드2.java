import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // step 1. 카드의 개수 n을 입력받는다.
        int n = Integer.parseInt(reader.readLine());

        Queue<Integer> numbers = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            numbers.offer(i);
        }

        // step 4. 1장이 남을 때까지 반복합니다.
        while (numbers.size() != 1) {
            // step 2. 가장 위에 있는 카드를 버립니다.
            numbers.poll();
            // step 3. 그 다음에 있는 카드를 가장 아래로 옮깁니다.
            numbers.offer(numbers.poll());
        }
        System.out.println(numbers.poll());
    }
}