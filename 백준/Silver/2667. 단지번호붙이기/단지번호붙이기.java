import java.io.*;
import java.util.*;

public class Main {
    private static final int[] DIRECT_ROW = {-1, 1, 0, 0};
    private static final int[] DIRECT_COL = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // step 1. 입력 값을 통해 맵을 구성
        int N = Integer.parseInt(reader.readLine());
        int[][] map = new int[N][N];

        for (int r = 0; r < N; r++) {
            String[] mapInfo = reader.readLine().split("");
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(mapInfo[c]);
            }
        }

        // step 2. bfs 탐색을 통해서 연결된 단지 탐색
        List<Integer> apartmentCounts = new ArrayList<>();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int apartmentCount = bfs(r, c, map);

                if (apartmentCount != 0) {
                    apartmentCounts.add(apartmentCount);
                }
            }
        }

        // step 3. 결과를 출력
        Collections.sort(apartmentCounts);

        StringBuilder builder = new StringBuilder();
        builder.append(apartmentCounts.size()).append("\n");
        for (int count : apartmentCounts) {
            builder.append(count).append("\n");
        }
        System.out.println(builder);
    }

    private static int bfs(int row, int col, int[][] map) {
        int apartmentCount = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{row, col});

        while (!queue.isEmpty()) {
            int[] polledUnit = queue.poll();

            if (map[polledUnit[0]][polledUnit[1]] == 0) {
                continue;
            }
            map[polledUnit[0]][polledUnit[1]] = 0;
            apartmentCount++;

            for (int index = 0; index < 4; index++) {
                int nextRow = polledUnit[0] + DIRECT_ROW[index];
                int nextCol = polledUnit[1] + DIRECT_COL[index];

                if (isInMap(nextRow, nextCol, map) && map[nextRow][nextCol] != 0) {
                    queue.add(new int[]{nextRow, nextCol});
                }
            }
        }

        return apartmentCount;
    }

    private static boolean isInMap(int row, int col, int[][] map) {
        return row >= 0 && row < map.length && col >= 0 && col < map.length;
    }
}
