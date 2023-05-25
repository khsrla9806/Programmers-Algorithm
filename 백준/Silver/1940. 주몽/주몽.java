import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] A = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        int answer = 0;
        int left = 0;
        int right = N - 1;
        while (left < right) {
            int result = A[left] + A[right];

            if (result > M) {
                right--;
            } else {
                if (result == M) {
                    answer++;
                }
                left++;
            }
        }
        System.out.println(answer);
    }
}