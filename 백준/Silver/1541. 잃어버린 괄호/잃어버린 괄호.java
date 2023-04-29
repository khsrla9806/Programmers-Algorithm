import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        String[] operation = reader.readLine().split("-");

        // 가장 앞에 존재하는 수
        tokenizer = new StringTokenizer(operation[0], "+");
        int answer = 0;
        while (tokenizer.hasMoreTokens()) {
            answer += Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 1; i < operation.length; i++) {
            tokenizer = new StringTokenizer(operation[i], "+");
            while (tokenizer.hasMoreTokens()) {
                answer -= Integer.parseInt(tokenizer.nextToken());
            }
        }

        System.out.println(answer);
    }
}