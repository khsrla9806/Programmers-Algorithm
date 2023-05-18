import java.io.*;
import java.util.*;

public class Main {
    static final int WHITE = 0;
    static final int BLUE = 1;
    static int white, blue;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs(0, 0, N);
        System.out.println(white);
        System.out.println(blue);
    }

    public static void bfs(int x, int y, int n) {
        if (isSameColor(x, y, n)) {
            return;
        }
        int divideN = n / 2;
        bfs(x, y, divideN);
        bfs(x, y + divideN, divideN);
        bfs(x + divideN, y, divideN);
        bfs(x + divideN, y + divideN, divideN);
    }

    public static boolean isSameColor(int x, int y, int n) {
        int color = map[x][y];
        if (n != 1) {
            for (int i = x; i < x + n; i++) {
                for (int j = y; j < y + n; j++) {
                    if (color != map[i][j]) {
                        return false;
                    }
                }
            }
        }
        if (color == WHITE) {
            white++;
        } else if (color == BLUE) {
            blue++;
        }
        return true;
    }
}
