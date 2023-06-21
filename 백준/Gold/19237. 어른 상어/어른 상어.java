import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTION = {{}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int N, M, K;
    static int[][] map;
    static Smell[][] smellMap;
    static LinkedList<Smell> smellInfo;
    static LinkedList<Smell> newSmell;
    static Shark[][] sharkMap;
    static ArrayList<Shark> sharkInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        smellMap = new Smell[N][N];
        sharkMap = new Shark[N][N];
        smellInfo = new LinkedList<>();
        newSmell = new LinkedList<>();
        sharkInfo = new ArrayList<>();
        // 맵 세팅
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                int number = Integer.parseInt(st.nextToken());
                map[row][col] = number;
                if (number > 0) {
                    Shark shark = new Shark(number, row, col);
                    sharkInfo.add(shark);
                    sharkMap[row][col] = shark;

                    // 처음 칸에 냄새를 뿌림
                    Smell smell = new Smell(shark.number, shark.row, shark.col, K);
                    map[row][col] = -1;
                    smellInfo.add(smell);
                    smellMap[smell.row][smell.col] = smell;
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
            while (!smellInfo.isEmpty()) {
                Smell smell = smellInfo.poll();
                smell.life--;

                if (smell.isValid()) {
                    newSmell.add(smell);
                } else {
                    smellMap[smell.row][smell.col] = null;
                    map[smell.row][smell.col] = 0;
                }
            }
            // 새로운 냄새를 다시 smellInfo 갱신
            while (!newSmell.isEmpty()) {
                Smell smell = newSmell.poll();
                map[smell.row][smell.col] = -1;
                smellInfo.add(smell);
                smellMap[smell.row][smell.col] = smell;
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
            // 이미 다른 상어가 있는 경우
            if (sharkMap[row][col] != null) {
                if (sharkMap[row][col].number < this.number) {
                    this.isAlive = false;
                } else {
                    Shark shark = sharkMap[row][col];
                    shark.isAlive = false;
                    sharkMap[row][col] = this;
                    // 해당 자리에 새로운 냄새를 뿌림 -> 이때 이미 있던 냄새는 제거된다. (다른 상어가 해당 칸에 들어올 수 있음을 의미)
                    Smell smell = new Smell(number, row, col, K);
                    newSmell.add(smell);
                    if (map[row][col] == -1) { // 이미 해당 자리에 냄새가 있었다면, 새롭게 덮어버림 기존 냄새 제거
                        map[row][col] = 0;
                        Smell beforeSmell = smellMap[row][col];
                        smellInfo.remove(beforeSmell);
                    }
                }
                M--;
            } else {
                sharkMap[row][col] = this;
                // 해당 자리에 새로운 냄새를 뿌림
                Smell smell = new Smell(number, row, col, K);
                newSmell.add(smell);
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
            return map[nextCors[0]][nextCors[1]] == 0;
        }

        private boolean isMySmell(int[] nextCors) {
            return smellMap[nextCors[0]][nextCors[1]].sharkNumber == this.number;
        }
    }

    static class Smell {
        int sharkNumber, row, col;
        int life;

        public Smell(int sharkNumber, int row, int col, int life) {
            this.sharkNumber = sharkNumber;
            this.row = row;
            this.col = col;
            this.life = life;
        }

        public boolean isValid() {
            return life != 0;
        }
    }
}