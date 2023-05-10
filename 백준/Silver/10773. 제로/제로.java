import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        Stack<Integer> numbers = new Stack<>();
        int answer = 0;
        for (int i = 0; i < k; i++) {
            int number = Integer.parseInt(br.readLine());
            if (number == 0 && !numbers.isEmpty()) {
                answer -= numbers.pop();
                continue;
            }
            numbers.push(number);
            answer += number;
        }
        System.out.println(answer);
    }
}