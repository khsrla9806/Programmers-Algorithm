import java.io.*;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // step 1 : 정수 t를 입력받습니다.
        int t = Integer.parseInt(reader.readLine());

        // step 2 : t번 만큼 반복하면서 "괄호 문자열"을 하니씩 스택에 넣습니다.
        Stack<Character> stack;
        for (int i = 0; i < t; i++) {
            stack = new Stack<>();
            char[] inputData = reader.readLine().toCharArray();
            for (char data : inputData) {
                if (stack.isEmpty()) {
                    stack.push(data);
                    continue;
                }
                // step 3 : 스택에 넣으면서 "("와 ")"가 만났을 때, 스택에서 제거합니다.
                if (stack.peek() == '(' && data == ')') {
                    stack.pop();
                } else {
                    stack.push(data);
                }
            }

            // step 4 : 만약 스택이 비어있다면 YES, 비어있지 않다면 NO를 출력합니다.
            if (stack.isEmpty()) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}