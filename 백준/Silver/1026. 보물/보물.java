import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // step 1. 입력 값을 정리
        int N = Integer.parseInt(reader.readLine());
        Integer[] A = new Integer[N];
        Integer[] B = new Integer[N];
        StringTokenizer tokenA = new StringTokenizer(reader.readLine(), " ");
        StringTokenizer tokenB = new StringTokenizer(reader.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(tokenA.nextToken());
            B[i] = Integer.parseInt(tokenB.nextToken());
        }

        // step 2. 최소인 S 값 탐색
        int S = 0;
        Arrays.sort(A);
        Arrays.sort(B, (num1, num2) -> num2 - num1);

        for (int i = 0; i < N; i++) {
            S += (A[i] * B[i]);
        }
        System.out.println(S);
    }
}
