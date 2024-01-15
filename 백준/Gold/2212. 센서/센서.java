import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int K = Integer.parseInt(reader.readLine());

        if (N == K) {
            System.out.println(0);
            return;
        }

        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        int[] sensors = new int[N];
        for (int i = 0; i < N; i++) {
            sensors[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(sensors);

        int[] diff = new int[N - 1];
        for (int i = 0; i < N - 1; i++) {
            diff[i] = sensors[i + 1] - sensors[i];
        }
        Arrays.sort(diff);

        int answer = 0;
        for (int i = 0; i < N - 1 - (K - 1); i++) {
            answer += diff[i];
        }
        System.out.println(answer);
    }
}
