import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // step 1. 입력값 정리
        int N = Integer.parseInt(reader.readLine());
        int[] route = new int[N - 1];
        int[] price = new int[N];
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
        for (int i = 0; i < N - 1; i++) route[i] = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(reader.readLine(), " ");
        for (int i = 0; i < N; i++) price[i] = Integer.parseInt(tokenizer.nextToken());

        // step 2. 최소 비용 탐색
        long answer = 0;
        long routes = route[0];
        int minPrice = price[0];

        for (int i = 1; i < N - 1; i++) {
            if (price[i] < minPrice) {
                answer += (minPrice * routes);
                routes = route[i];
                minPrice = price[i];
                continue;
            }
            routes += route[i];
        }
        answer += (minPrice * routes);
        System.out.println(answer);
    }
}
