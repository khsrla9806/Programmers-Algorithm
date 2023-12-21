import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Main {
    private static int[][] paper;
    private static int N, minusCount, zeroCount, plusCount;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // step 1. process input data
        N = Integer.parseInt(reader.readLine());
        paper = new int[N + 1][N + 1];

        for (int r = 1; r <= N; r++) {
            String[] temp = reader.readLine().split(" ");
            for (int c = 1; c <= N; c++) {
                paper[r][c] = Integer.parseInt(temp[c - 1]);
            }
        }

        // step 2. search
        search(new int[]{1, 1}, N);

        // step 3. print answer
        System.out.println(minusCount + "\n" + zeroCount + "\n" + plusCount);
    }

    private static void search(int[] startCors, int k) {
        if (isAvailablePaper(startCors, k)) {
            plusCountOfAnswer(paper[startCors[0]][startCors[1]]);
            return;
        }

        // 9 등분
        int nextK = k / 3;
        for (int r = startCors[0]; r < startCors[0] + k; r += nextK) {
            for (int c = startCors[1]; c < startCors[1] + k; c += nextK) {
                int[] nextStartCors = new int[]{r, c};
                search(nextStartCors, nextK);
            }
        }
    }

    /**
     * 시작 좌표 startCors, 가중치 k를 받아서 사용 가능한 종이인지 확인하는 메서드
     */
    private static boolean isAvailablePaper(int[] startCors, int k) {
        int[] endCors = new int[]{startCors[0] + k, startCors[1] + k};

        int initValue = paper[startCors[0]][startCors[1]];

        for (int r = startCors[0]; r < endCors[0]; r++) {
            for (int c = startCors[1]; c < endCors[1]; c++) {
                int targetValue = paper[r][c];
                if (targetValue != initValue) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void plusCountOfAnswer(int value) {
        if (value == -1) {
            minusCount++;
        } else if (value == 0) {
            zeroCount++;
        } else if (value == 1) {
            plusCount++;
        }
    }
}
