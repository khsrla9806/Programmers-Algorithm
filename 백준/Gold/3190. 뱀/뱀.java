import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int n;
    static int[][] map;
    static boolean[][] snakeStatus;
    static final int[][][] DIRECTION = {
            {{-1, 0}, {1, 0}},
            {{1, 0}, {-1, 0}},
            {{0, -1}, {0, 1}},
            {{0, 1}, {0, -1}}
    }; // 왼쪽, 오른쪽, 아래, 위 방향 전환

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(reader.readLine());
        map = new int[n + 1][n + 1]; // index 0은 사용하지 않음
        snakeStatus = new boolean[n + 1][n + 1];
        snakeStatus[1][1] = true; // 뱀의 초기 위치

        int k = Integer.parseInt(reader.readLine());
        for (int i = 0; i < k; i++) {
            String[] apple = reader.readLine().split(" ");
            map[Integer.parseInt(apple[0])][Integer.parseInt(apple[1])] = 1;
        }

        int l = Integer.parseInt(reader.readLine());
        HashMap<Integer, String> directions = new HashMap<>();
        for (int i = 0; i < l; i++) {
            String[] direction = reader.readLine().split(" ");
            directions.put(Integer.parseInt(direction[0]), direction[1]);
        }

        int time = 0;
        int[] pos = {1, 1};
        int[] cors = {0, 1}; // 초기 방향은 오른쪽
        Queue<int[]> snake = new LinkedList<>(); // 뱀의 몸 길이를 저장할 큐
        snake.offer(pos);
        while (true) {
            time++;
            int[] next = new int[]{pos[0] + cors[0], pos[1] + cors[1]};
            snake.offer(next);

            // 게임이 끝나는지 확인
            if (isGameEnd(next)) break;

            if (map[next[0]][next[1]] != 1) {
                int[] tail = snake.poll();
                snakeStatus[tail[0]][tail[1]] = false;
            } else {
                map[next[0]][next[1]] = 0;
            }
            snakeStatus[next[0]][next[1]] = true;

            // 뱀의 이동 방향이 변경되는지 확인 = null이 아니면 방향의 변경이 있다는 말
            if (directions.get(time) != null) {
                String direct = directions.get(time);
                int corsX = cors[0];
                int corsY = cors[1];
                int value;
                if (corsX == 0 && corsY == -1) { // 왼쪽 이동중
                    value = 0;
                } else if (corsX == 0 && corsY == 1) { // 오른쪽 이동중
                    value = 1;
                } else if (corsX == 1 && corsY == 0) { // 아래쪽 이동중
                    value = 2;
                } else { // 위쪽 이동중
                    value = 3;
                }
                cors = direct.equals("D") ? DIRECTION[value][0] : DIRECTION[value][1];
            }
            pos = next;
        }
        System.out.println(time);
    }

    public static boolean isGameEnd(int[] next) {
        // (1) 이동 후 벽과 부딪혔을 때 게임 끝
        if (next[0] > n || next[0] < 1 || next[1] > n || next[1] < 1) {
            return true;
        }
        // (2) 이동 후 자신의 몸과 부딪혔을 때 게임 끝
        if (snakeStatus[next[0]][next[1]]) {
            return true;
        }
        return false;
    }
}