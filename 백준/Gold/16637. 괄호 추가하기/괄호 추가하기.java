import java.io.*;
import java.util.*;

public class Main {
    static int N, max, operatorCnt, answer;
    static String[] input;
    static boolean[] operatorVisited;
    static LinkedList<String> list = new LinkedList<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        max = (N + 1) / 4; // 최대 선택 가능한 괄호의 개수
        operatorCnt = (N - 1) / 2; // 연산자의 개수
        operatorVisited = new boolean[operatorCnt];
        input = br.readLine().split("");

        String operator = "+";
        answer = 0;
        for (int i = 0; i < N; i++) {
            if (isOperator(input[i])) {
                operator = input[i];
            } else {
                answer = calculateWithOperator(answer, operator, Integer.parseInt(input[i]));
            }
        }

        for (int i = 1; i <= max; i++) {
            find(0, i);
        }
        System.out.println(answer);
    }

    public static void find(int start, int cnt) {
        if (cnt == 0) {
            for (int i = 0; i < N; i++) {
                if (isOperator(input[i]) && operatorVisited[(i - 1) / 2]) { // 선택된 연산자일 경우
                    int before = Integer.parseInt(list.pollLast());
                    int next = Integer.parseInt(input[i + 1]);
                    String operator = input[i];
                    int resultValue = calculateWithOperator(before, operator, next);
                    list.add(convertToString(resultValue));
                    i++;
                } else {
                    list.add(input[i]);
                }
            }
            String operator = "+";
            int result = 0;
            while (!list.isEmpty()) {
                String value = list.pollFirst();
                if (isOperator(value)) { // 연산자라면
                    operator = value;
                } else { // 숫자라면
                    result = calculateWithOperator(result, operator, Integer.parseInt(value));
                }
            }
            answer = Math.max(answer, result);
            return;
        }

        for (int i = start; i < operatorCnt; i++) {
            operatorVisited[i] = true;
            find(i + 2, cnt - 1);
            operatorVisited[i] = false;
        }
    }

    public static int calculateWithOperator(int firstValue, String operator, int secondValue) {
        if (operator.equals("+")) {
            return firstValue + secondValue;
        } else if (operator.equals("-")) {
            return firstValue - secondValue;
        }
        return firstValue * secondValue;
    }

    public static boolean isOperator(String value) {
        return value.equals("+") || value.equals("-") || value.equals("*");
    }

    public static String convertToString(int value) {
        return String.valueOf(value);
    }
}