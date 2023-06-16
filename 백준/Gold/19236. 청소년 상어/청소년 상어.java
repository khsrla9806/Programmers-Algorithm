import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTION = {
            {}, {-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}
    };
    
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[4][4];
        TreeMap<Integer, Fish> fishInfo = new TreeMap<>();
        Fish startFish = null;
        for (int row = 0; row < 4; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < 4; col++) {
                int fishNumber = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken());
                Fish fish = new Fish(fishNumber, row, col, direction ,false);
                if (row == 0 && col == 0) {
                    startFish = fish;
                }
                fishInfo.put(fishNumber, fish);
                map[row][col] = fishNumber;
            }
        }

        Shark shark = new Shark(0, 0, startFish.direction);
        shark.eat(startFish, map);
        dfs(shark, map, fishInfo);
        System.out.println(answer);
    }

    static void dfs(Shark shark, int[][] map, TreeMap<Integer, Fish> fishInfo) {
        for (Fish fish : fishInfo.values()) {
            if (!fish.dead) {
                fish.move(8, map, fishInfo);
            }
        }
        ArrayList<Integer> fishes = shark.findFish(map);
        if (fishes.isEmpty()) {
            answer = Math.max(answer, shark.score);
            return;
        }
        for (int number : fishes) {
            TreeMap<Integer, Fish> copyFishInfo = copyFishInfo(fishInfo);
            int[][] copyMap = copyMap(map);
            Shark copyShark = copyShark(shark);
            Fish fish = copyFishInfo.get(number);
            copyShark.eat(fish, copyMap);
            dfs(copyShark, copyMap, copyFishInfo);
        }
    }

    static Shark copyShark(Shark shark) {
        return new Shark(shark);
    }

    static TreeMap<Integer, Fish> copyFishInfo(TreeMap<Integer, Fish> fishInfo) {
        TreeMap<Integer, Fish> temp = new TreeMap<>();
        for (int i = 1; i <= fishInfo.size(); i++) {
            Fish fish = fishInfo.get(i);
            temp.put(i, new Fish(fish.number, fish.row, fish.col, fish.direction, fish.dead));
        }
        return temp;
    }

    static int[][] copyMap(int[][] map) {
        int[][] temp = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = map[i][j];
            }
        }
        return temp;
    }

    static class Fish {
        int number, row, col;
        int direction;
        boolean dead;

        public Fish(int number, int row, int col, int direction, boolean dead) {
            this.number = number;
            this.row = row;
            this.col = col;
            this.direction = direction;
            this.dead = dead;
        }

        public void move(int cnt, int[][] map, TreeMap<Integer, Fish> fishInfo) {
            int[] nextCors = getNextCors();
            if (isValid(nextCors, map)) {
                int nextFishNumber = map[nextCors[0]][nextCors[1]];
                if (nextFishNumber > 0) {
                    Fish fish = fishInfo.get(nextFishNumber);
                    swap(fish, map);
                } else { // 상어가 아니라는 것이 isValid에서 걸러졌기 때문에 가능
                    map[row][col] = -1;
                    row = nextCors[0];
                    col = nextCors[1];
                    map[row][col] = number;
                }
            } else {
                direction = (direction + 1) == 9 ? 1 : direction + 1;
                if (cnt == 0) {
                    return;
                }
                move(cnt - 1, map, fishInfo);
            }
        }

        private boolean isValid(int[] nextCors, int[][] map) {
            return (nextCors[0] >= 0 && nextCors[1] >= 0 && nextCors[0] < 4 && nextCors[1] < 4)
                    && map[nextCors[0]][nextCors[1]] != 0;
        }

        private int[] getNextCors() {
            int[] direct = DIRECTION[direction];
            int nextR = row + direct[0];
            int nextC = col + direct[1];

            return new int[]{nextR, nextC};
        }

        private void swap(Fish fish, int[][] map) {
            int tempR = row;
            int tempC = col;
            row = fish.row;
            col = fish.col;
            fish.row = tempR;
            fish.col = tempC;
            map[row][col] = number;
            map[fish.row][fish.col] = fish.number;
        }
    }

    static class Shark {
        int row, col, score;
        int direction;

        public Shark(int row, int col, int direction) {
            score = 0;
            this.row = row;
            this.col = col;
            this.direction = direction;
        }

        public Shark(Shark shark) {
            this.row = shark.row;
            this.col = shark.col;
            this.score = shark.score;
            this.direction = shark.direction;
        }

        public void eat(Fish fish, int[][] map) {
            map[row][col] = -1;
            row = fish.row;
            col = fish.col;
            direction = fish.direction;
            score += fish.number;
            map[row][col] = 0;
            fish.dead = true;
        }

        public ArrayList<Integer> findFish(int[][] map) { // 눈 앞에 보이는 물고기들을 모두 확인
            int[] direct = DIRECTION[direction];
            int[] curr = {row, col};
            curr[0] += direct[0];
            curr[1] += direct[1];
            ArrayList<Integer> fishes = new ArrayList<>();
            while (!isEnd(curr)) {
                int fishNumber = map[curr[0]][curr[1]];
                if (fishNumber > 0) {
                    fishes.add(fishNumber);
                }
                curr[0] += direct[0];
                curr[1] += direct[1];
            }
            return fishes;
        }

        public boolean isEnd(int[] nextCors) {
            return nextCors[0] < 0 || nextCors[1] < 0 || nextCors[0] >= 4 || nextCors[1] >= 4;
        }

        private int[] getNextCors() {
            int[] direct = DIRECTION[direction];
            int nextR = row + direct[0];
            int nextC = col + direct[1];

            return new int[]{nextR, nextC};
        }
    }
}