import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int[][] DIRECT = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int day, todayTomatoCnt, zeroTomatoCnt, m, n;
    static int[][] tomatoes;
    static boolean[][] tomatoesStatus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        tomatoes = new int[n][m];
        tomatoesStatus = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                tomatoes[i][j] = Integer.parseInt(st.nextToken());
                if (tomatoes[i][j] == 1) {
                    tomatoesStatus[i][j] = true;
                    queue.offer(new int[]{i, j});
                    todayTomatoCnt++;
                } else if (tomatoes[i][j] == 0) {
                    zeroTomatoCnt++;
                }
            }
        }

        while (!queue.isEmpty()) {
            // 그날 확인한 토마토를 모두 탐색 완료
            if (todayTomatoCnt == 0) {
                todayTomatoCnt = queue.size();
                day++;
            }
            int[] currTomato = queue.poll();
            todayTomatoCnt--;
            int tN = currTomato[0];
            int tM = currTomato[1];

            // 4 방향의 토마토를 확인
            for (int[] direct : DIRECT) {
                int nextN = tN + direct[0];
                int nextM = tM + direct[1];
                if (nextN >= 0 && nextM >= 0 && nextN < n && nextM < m) {
                    if (tomatoes[nextN][nextM] == 0 && !tomatoesStatus[nextN][nextM]) {
                        queue.offer(new int[]{nextN, nextM});
                        zeroTomatoCnt--;
                        tomatoesStatus[nextN][nextM] = true;
                    }
                }
            }
        }

        if (zeroTomatoCnt == 0) { // 안 익은 토마토를 모두 익히는데 성공한 경우
            System.out.println(day);
        } else { // 안 익은 토마토가 남은 경우
            System.out.println(-1);
        }
    }
}