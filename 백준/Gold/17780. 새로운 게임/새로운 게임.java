import java.io.*;
import java.util.*;

public class Main {
    static final int WHITE = 0, RED = 1, BLUE = 2, MAX_COUNT = 1000, END = 4;
    static final int[][] DIRECTION = {{}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static int N, K, answer;
    static int[][] map;
    static LinkedList<Horse>[][] horseList;
    static HashMap<Integer, Horse> horseMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        horseList = new LinkedList[N + 1][N + 1];
        horseMap = new HashMap<>();
        map = new int[N + 1][N + 1];
        for (int row = 0; row <= N; row++) {
            for (int col = 0; col <= N; col++) {
                horseList[row][col] = new LinkedList<>();
            }
        }

        // 체스판 정보 입력
        for (int row = 1; row <= N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 말 정보 입력
        for (int number = 1; number <= K; number++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            LinkedList<Horse> currentLocation = horseList[row][col];
            Horse horse = new Horse(number, direction, row, col);
            currentLocation.add(horse);
            horseMap.put(number, horse);
        }

        answer = gameStart();
        System.out.println(answer);
    }

    public static int gameStart() {
        int result = 0;
        while (result < 1000) {
            // 반복문 1번이 한턴
            for (int number = 1; number <= K; number++) {
                Horse currHorse = horseMap.get(number);
                if (currHorse.isBottom()) {
                    int[] nextCors = currHorse.getNextCors();
                    if (!isValid(nextCors) || map[nextCors[0]][nextCors[1]] == BLUE) {
                        // 파란색이거나 맵을 벗어나면 방향을 바꾸고 갱신
                        currHorse.reverseDirection();
                        nextCors = currHorse.getNextCors();
                    }

                    if (isValid(nextCors)) {
                        LinkedList<Horse> currentList = horseList[currHorse.row][currHorse.col];
                        LinkedList<Horse> nextList = horseList[nextCors[0]][nextCors[1]];

                        // 흰색
                        if (map[nextCors[0]][nextCors[1]] == WHITE) {
                            while (!currentList.isEmpty()) {
                                Horse horse = currentList.pollFirst();
                                horse.row = nextCors[0];
                                horse.col = nextCors[1];
                                nextList.add(horse);
                                horse.refreshCurrentLocation();
                            }
                        }
                        // 빨간색
                        else if (map[nextCors[0]][nextCors[1]] == RED) {
                            while (!currentList.isEmpty()) {
                                Horse horse = currentList.pollLast();
                                horse.row = nextCors[0];
                                horse.col = nextCors[1];
                                nextList.add(horse);
                                horse.refreshCurrentLocation();
                            }
                        }

                        if (nextList.size() >= 4) {
                            return result + 1;
                        }
                    }
                }
            }
            result++;
        }
        return -1;
    }

    public static boolean isValid(int[] cors) {
        return cors[0] >= 1 && cors[1] >= 1 && cors[0] <= N && cors[1] <= N;
    }

    static class Horse {
        int number, direction, row, col;
        List<Horse> currentLocation;

        public Horse(int number, int direction, int row, int col) {
            this.number = number;
            this.direction = direction;
            this.row = row;
            this.col = col;
            refreshCurrentLocation();
        }

        public void refreshCurrentLocation() {
            this.currentLocation = horseList[row][col];
        }

        // 현재 위치에서 가장 아래있는 말인지 확인
        public boolean isBottom() {
            return this.equals(currentLocation.get(0));
        }

        public int[] getNextCors() {
            return new int[]{row + DIRECTION[direction][0], col + DIRECTION[direction][1]};
        }

        public void reverseDirection() {
            if (direction == 1) direction = 2;
            else if (direction == 2) direction = 1;
            else if (direction == 3) direction = 4;
            else if (direction == 4) direction = 3;
        }
    }
}