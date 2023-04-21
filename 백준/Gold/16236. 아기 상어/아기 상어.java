import java.io.*;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        int N = Integer.parseInt(reader.readLine());
        int[][] map = new int[N][N];
        PriorityQueue<Fish> fishes = new PriorityQueue<>();
        Queue<int[]> curs = new LinkedList<>();


        // step 1. 먹을 수 있는 물고기가 존재하는지 확인 (그래프 그리면서)
        for (int i = 0; i < N; i++) {
            String[] data = reader.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(data[j]);
                if (map[i][j] == 9) {
                    curs.offer(new int[]{i, j});
                    map[i][j] = 0;
                }
            }
        }

        int answer = 0;
        int sharkSize = 2;
        int eatCnt = 0;
        
        // step 2. 탐색 시작 (bfs) - 먹을 수 있는 물고기를 모두 찾아서 Queue에 넣음
        while (true) {
            fishes.clear();
            int[][] dist = new int[N][N];
            while (!curs.isEmpty()) {
                int[] curr = curs.poll();

                for (int i = 0; i < 4; i++) {
                    int nX = curr[0] + dx[i];
                    int nY = curr[1] + dy[i];

                    if (nX >= 0 && nX < N && nY >= 0 && nY < N && map[nX][nY] <= sharkSize && dist[nX][nY] == 0) {
                        curs.offer(new int[]{nX, nY});
                        dist[nX][nY] = dist[curr[0]][curr[1]] + 1;
                        if (map[nX][nY] != 0 && map[nX][nY] < sharkSize) {
                            fishes.offer(new Fish(nX, nY, dist[nX][nY]));
                        }
                    }
                }
            }

            if (fishes.isEmpty()) {
                break;
            }

            Fish target = fishes.poll();
            answer += target.dist;
            map[target.x][target.y] = 0;

            if (++eatCnt == sharkSize) {
                sharkSize++;
                eatCnt = 0;
            }
            curs.offer(new int[]{target.x, target.y});
        }
        System.out.println(answer);
    }
}

class Fish implements Comparable<Fish> {
    int x;
    int y;
    int dist;
    public Fish(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }

    @Override
    public int compareTo(Fish obj) {
        if (this.dist == obj.dist) {
            if (this.x == obj.x) {
                return this.y - obj.y;
            }
            return this.x - obj.x;
        }
        return this.dist - obj.dist;
    }
}