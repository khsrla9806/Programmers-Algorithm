import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // step 1. 입력값 정리
        String[] NL = reader.readLine().split(" ");
        int N = Integer.parseInt(NL[0]);
        int L = Integer.parseInt(NL[1]);

        int[] pipe = new int[1001];
        int minPoint = 1;
        int maxPoint = 1000;
        String[] leaks = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            int leakPoint = Integer.parseInt(leaks[i]);
            minPoint = Math.min(minPoint, leakPoint);
            maxPoint = Math.max(maxPoint, leakPoint);
            pipe[leakPoint] = -1;
        }

        // step 2. 필요한 최소 테이프 개수 탐색
        int tapeCount = 0;
        for (int point = minPoint; point <= maxPoint; point++) {
            if (pipe[point] == -1) {
                tapeCount++;
                point += (L - 1);
            }
        }
        System.out.println(tapeCount);
    }
}
