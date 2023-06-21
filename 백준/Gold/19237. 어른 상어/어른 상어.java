import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTION = {{}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int N, M, K;
    static int[][] smells;
    static int[][] smellLife;
    static Shark[][] sharkMap;
    static ArrayList<Shark> sharkInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        smells = new int[N][N];
        smellLife = new int[N][N];
        sharkMap = new Shark[N][N];
        sharkInfo = new ArrayList<>();
        // 맵 세팅
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                int number = Integer.parseInt(st.nextToken());
                if (number > 0) {
                    Shark shark = new Shark(number, row, col);
                    sharkInfo.add(shark);
                    sharkMap[row][col] = shark;
                    smells[row][col] = shark.number;
                    smellLife[row][col] = K;
                }
            }
        }
        // 상어 방향 세팅
        sharkInfo.sort((s1, s2) -> s1.number - s2.number);
        st = new StringTokenizer(br.readLine());
        for (int number = 1; number <= M; number++) {
            int direction = Integer.parseInt(st.nextToken());
            sharkInfo.get(number - 1).setDirection(direction);
        }
        // 우선순위 세팅
        for (int number = 1; number <= M; number++) {
            Shark shark = sharkInfo.get(number - 1);
            for (int direction = 1; direction <= 4; direction++) {
                String[] inputPriority = br.readLine().split(" ");
                shark.setPriority(direction, inputPriority);
            }
        }
        // 시작
        int answer = start();
        System.out.println(answer);
    }

    public static int start() {
        int time = 0;
        while (++time <= 1000) {
            // 상어 탐색
            for (Shark shark : sharkInfo) {
                if (shark.isAlive) {
                    // 상어가 이동 가능한 공간을 찾음
                    int[] nextCors = shark.getNextCors();
                    shark.move(nextCors);
                }
            }

            // 냄새 탐색
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    if (smells[row][col] != 0) {
                        smellLife[row][col]--;
                        if (smellLife[row][col] == 0) {
                            smells[row][col] = 0;
                        }
                    }
                }
            }

            // 살아있는 상어들의 냄새 뿌리기
            for (Shark shark : sharkInfo) {
                if (shark.isAlive) {
                    smells[shark.row][shark.col] = shark.number;
                    smellLife[shark.row][shark.col] = K;
                }
            }

            if (M == 1) {
                return time;
            }
        }
        return -1;
    }

    public static boolean isValidCors(int[] cors) {
        return cors[0] >= 0 && cors[1] >= 0 && cors[0] < N && cors[1] < N;
    }

    static class Shark {
        int number, row, col;
        int direction;
        boolean isAlive;
        int[][] priority;

        public Shark(int number, int row, int col) {
            this.number = number;
            this.row = row;
            this.col = col;
            direction = 0;
            isAlive = true;
            priority = new int[5][4]; // 0, 위, 아래, 왼쪽, 오른쪽
        }

        public void move(int[] nextCors) {
            sharkMap[row][col] = null;
            row = nextCors[0];
            col = nextCors[1];
            if (sharkMap[row][col] == null || sharkMap[row][col].number > number) {
                if (sharkMap[row][col] != null) {
                    sharkMap[row][col].isAlive = false;
                    M--;
                }
                sharkMap[row][col] = this;
            } else {
                this.isAlive = false;
                M--;
            }
        }

        public void setDirection(int direction) {
            this.direction = direction;
        }

        public void setPriority(int direction, String[] inputData) {
            for (int i = 0; i < 4; i++) {
                priority[direction][i] = Integer.parseInt(inputData[i]);
            }
        }

        public int[] getNextCors() {
            for (int dir : priority[direction]) {
                int[] nextDir = DIRECTION[dir];
                int[] nextCors = {row + nextDir[0], col + nextDir[1]};
                if (isValidCors(nextCors) && isBlank(nextCors)) {
                    setDirection(dir);
                    return nextCors;
                }
            }
            for (int dir : priority[direction]) {
                int[] nextDir = DIRECTION[dir];
                int[] nextCors = {row + nextDir[0], col + nextDir[1]};
                if (isValidCors(nextCors) && isMySmell(nextCors)) {
                    setDirection(dir);
                    return nextCors;
                }
            }
            return new int[]{row, col};
        }

        private boolean isBlank(int[] nextCors) {
            return smells[nextCors[0]][nextCors[1]] == 0;
        }

        private boolean isMySmell(int[] nextCors) {
            return smells[nextCors[0]][nextCors[1]] == this.number;
        }
    }
}