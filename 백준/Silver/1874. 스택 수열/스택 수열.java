import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();

        int num = 1;
        for (int i = 0; i < n; i++) {
            int inputNum = Integer.parseInt(reader.readLine());

            while (num <= inputNum) {
                stack.push(num++);
                builder.append("+").append("\n");
            }

            if (stack.peek() == inputNum) {
                stack.pop();
                builder.append("-").append("\n");
            } else {
                builder = new StringBuilder("NO");
                break;
            }
        }

        System.out.println(builder);

    }
}