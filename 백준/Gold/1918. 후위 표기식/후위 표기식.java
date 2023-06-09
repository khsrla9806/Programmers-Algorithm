import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String notation = br.readLine();

        Stack<Character> operator = new Stack<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < notation.length(); i++) {
            char value = notation.charAt(i);
            if (value >= 'A' && value <= 'Z') {
                builder.append(value);
            } else if (value == '(') {
                operator.add(value);
            } else if (value == ')') {
                while (!operator.isEmpty()) {
                    char popVal = operator.pop();
                    if (popVal == '(') {
                        break;
                    }
                    builder.append(popVal);
                }
            } else {
                while (!operator.isEmpty() && checkPriority(operator.peek(), value)) {
                    builder.append(operator.pop());
                }
                operator.add(value);
            }
        }
        while (!operator.isEmpty()) {
            builder.append(operator.pop());
        }
        System.out.println(builder);
    }

    public static boolean checkPriority(char peekVal, char val) {
        int peekValWeight = setWeight(peekVal);
        int valWeight = setWeight(val);
        return peekValWeight >= valWeight;
    }

    public static int setWeight(char val) {
        if (val == '(') return 0;
        return val == '+' || val == '-' ? 1 : 2;
    }
}