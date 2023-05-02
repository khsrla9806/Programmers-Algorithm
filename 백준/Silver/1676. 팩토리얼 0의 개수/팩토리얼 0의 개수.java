import java.io.*;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        BigDecimal result = new BigDecimal(1);
        for (int i = 1; i <= n; i++) {
            result = result.multiply(BigDecimal.valueOf(i));
        }

        int answer = 0;
        char[] values = String.valueOf(result).toCharArray();
        for (int i = values.length - 1; i >= 0; i--) {
            if (values[i] == '0') {
                answer++;
            } else {
                break;
            }
        }

        System.out.println(answer);
    }
}