import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[] ropes = new int[N];
        for (int i = 0; i < N; i++) ropes[i] = Integer.parseInt(reader.readLine());
        Arrays.sort(ropes);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer = Math.max(answer, ropes[i] * (N - i));
        }
        System.out.println(answer);
    }
}
