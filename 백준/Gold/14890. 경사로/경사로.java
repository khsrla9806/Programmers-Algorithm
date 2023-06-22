import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        // 왼쪽에서 오른쪽 (한쪽이 되면 반대쪽도 된다)
        for (int row = 0; row < N; row++) {
            boolean isFail = false;
            int before = 0;
            int length = 0;
            for (int col = 0; col < N; col++) {
                if (before == 0 || before == map[row][col]) {
                    length++;
                } else if (before < map[row][col]) {
                    if (map[row][col] != before + 1 || length < L) {
                        isFail = true;
                    } else {
                        length = 1;
                    }
                } else if (before > map[row][col]) {
                    if (map[row][col] + 1 != before) {
                        isFail = true;
                        break;
                    }
                    int target = map[row][col];
                    for (int i = col; i < col + L; i++) {
                        if (i >= N || target != map[row][i]) {
                            isFail = true;
                            break;
                        }
                    }
                    col = col + L - 1;
                    length = 0;
                }
                if (isFail) break;
                before = map[row][col];
            }
            if (!isFail) {
                answer++;
            }
        }
        
        // 위에서 아래쪽
        for (int col = 0; col < N; col++) {
            boolean isFail = false;
            int before = 0;
            int length = 0;
            for (int row = 0; row < N; row++) {
                if (before == 0 || before == map[row][col]) {
                    length++;
                } else if (before < map[row][col]) {
                    if (map[row][col] != before + 1 || length < L) {
                        isFail = true;
                    } else {
                        length = 1;
                    }
                } else if (before > map[row][col]) {
                    if (map[row][col] + 1 != before) {
                        isFail = true;
                        break;
                    }
                    int target = map[row][col];
                    for (int i = row; i < row + L; i++) {
                        if (i >= N || target != map[i][col]) {
                            isFail = true;
                            break;
                        }
                    }
                    row = row + L - 1;
                    length = 0;
                }
                if (isFail) break;
                before = map[row][col];
            }
            if (!isFail) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}