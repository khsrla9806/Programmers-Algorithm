import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static final int[][] DIRECTION = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[][] map = new int[101][101];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            int[] direction = DIRECTION[d];
            ArrayList<int[]> dragonCors = new ArrayList<>();
            dragonCors.add(new int[]{y, x});
            dragonCors.add(new int[]{y + direction[0], x + direction[1]});

            for (int generation = 1; generation <= g; generation++) {
                int size = dragonCors.size();
                for (int j = size - 1; j > 0; j--) {
                    int[] firstCors = dragonCors.get(j - 1);
                    int[] secondCors = dragonCors.get(j);
                    int[] curDirection = {firstCors[0] - secondCors[0], firstCors[1] - secondCors[1]};
                    int[] rotatedDirection = getRotateDirection(curDirection);
                    int[] criteria = dragonCors.get(dragonCors.size() - 1);
                    dragonCors.add(new int[]{criteria[0] + rotatedDirection[0], criteria[1] + rotatedDirection[1]});
                }
            }

            for (int[] cors : dragonCors) {
                map[cors[0]][cors[1]] = 1;
            }
        }

        int answer = 0;
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                if (map[y][x] == 1 && map[y][x + 1] == 1 && map[y + 1][x] == 1 && map[y + 1][x + 1] == 1) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }

    private static int[] getRotateDirection(int[] curDirection) {
        int dy = curDirection[0];
        int dx = curDirection[1];

        if (dy == 0 && dx == 1) {
            return new int[]{1, 0};
        } else if (dy == -1 && dx == 0) {
            return new int[]{0, 1};
        } else if (dy == 0 && dx == -1) {
            return new int[]{-1, 0};
        }
        return new int[]{0, -1};
    }
}
