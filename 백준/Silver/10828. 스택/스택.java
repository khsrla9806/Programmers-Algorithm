import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        StringTokenizer tokenizer;

        int n = Integer.parseInt(reader.readLine());
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(reader.readLine(), " ");
            String operation = tokenizer.nextToken();
            if (tokenizer.hasMoreTokens()) { // Push 연산
                stack.push(tokenizer.nextToken());
            } else { // top, pop, empty, size 연산
                if (operation.equals("size")) {
                    builder.append(stack.size()).append("\n");
                } else if (operation.equals("pop")) {
                    builder.append(stack.isEmpty() ? -1 : stack.pop()).append("\n");
                } else if (operation.equals("empty")) {
                    builder.append(stack.isEmpty() ? 1 : 0).append("\n");
                } else if (operation.equals("top")) {
                    builder.append(stack.isEmpty() ? -1 : stack.peek()).append("\n");
                }
            }
        }
        System.out.println(builder);
    }
}