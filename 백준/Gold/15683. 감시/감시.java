import java.io.*;
import java.util.*;

public class Main {
    static final int[][] DIRECTION = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int N, M, K, answer = Integer.MAX_VALUE;
    static int[][] originMap;
    static ArrayList<CCTV> cctvList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = 0;
        originMap = new int[N][M];
        cctvList = new ArrayList<>();
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                int number = Integer.parseInt(st.nextToken());
                originMap[row][col] = number;

                if (number >= 1 && number <= 5) {
                    cctvList.add(makeCCTV(number, row, col));
                    K++;
                }
            }
        }
        start(0, originMap);
        System.out.println(answer);
    }

    public static void start(int index, int[][] map) {
        if (index >= K) {
            int result = 0;
            for (int row = 0; row < map.length; row++) {
                for (int col = 0; col < map[0].length; col++) {
                    if (map[row][col] == 0) result++;
                }
            }
            answer = Math.min(answer, result);
            return;
        }
        CCTV cctv = cctvList.get(index);
        while (cctv.isValid()) {
            int[][] copyMap = copyMap(map);
            cctv.find(copyMap);
            start(index + 1, copyMap);
            cctv.move();
        }
        cctv.setCount();
    }

    public static int[][] copyMap(int[][] map) {
        int[][] temp = new int[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                temp[i][j] = map[i][j];
            }
        }
        return temp;
    }

    public static CCTV makeCCTV(int number, int row, int col) {
        if (number == 1) return new FirstCCTV(row, col);
        else if (number == 2) return new SecondCCTV(row, col);
        else if (number == 3) return new ThirdCCTV(row, col);
        else if (number == 4) return new FourthCCTV(row, col);
        else return new FifthCCTV(row, col);
    }

    interface CCTV {
        void move();
        void find(int[][] map);
        boolean isValid();
        void setCount();
        default void search(int row, int col, int[] direction, int[][] map) {
            int nextR = row + direction[0];
            int nextC = col + direction[1];
            while (nextR >= 0 && nextC >= 0 && nextR < N && nextC < M) {
                if (map[nextR][nextC] == 6) {
                    break;
                } else if (map[nextR][nextC] == 0) {
                    map[nextR][nextC] = -1;
                }
                nextR += direction[0];
                nextC += direction[1];
            }
        }
    }

    static class FirstCCTV implements CCTV {
        int row, col, count;
        int direction1;

        public FirstCCTV(int row, int col) {
            this.row = row;
            this.col = col;
            setCount();
            direction1 = 0;
        }

        public void setCount() {
            count = 4;
        }

        public void move() {
            if (count != 0) {
                direction1 = (direction1 + 1) % 4;
                count--;
            }
        }

        public void find(int[][] map) {
            search(row, col, DIRECTION[direction1], map);
        }

        public boolean isValid() {
            return count != 0;
        }
    }

    static class SecondCCTV implements CCTV {
        int row, col, count;
        int direction1, direction2;

        public SecondCCTV(int row, int col) {
            this.row = row;
            this.col = col;
            setCount();
            direction1 = 0;
            direction2 = 2;
        }

        public void setCount() {
            count = 2;
        }

        public void move() {
            if (count != 0) {
                direction1 = (direction1 + 1) % 4;
                direction2 = (direction2 + 1) % 4;
                count--;
            }
        }

        public void find(int[][] map) {
            search(row, col, DIRECTION[direction1], map);
            search(row, col, DIRECTION[direction2], map);
        }

        public boolean isValid() {
            return count != 0;
        }
    }

    static class ThirdCCTV implements CCTV {
        int row, col, count;
        int direction1, direction2;

        public ThirdCCTV(int row, int col) {
            this.row = row;
            this.col = col;
            setCount();
            direction1 = 3;
            direction2 = 0;
        }

        public void setCount() {
            count = 4;
        }

        public void move() {
            if (count != 0) {
                direction1 = (direction1 + 1) % 4;
                direction2 = (direction2 + 1) % 4;
                count--;
            }
        }

        public void find(int[][] map) {
            search(row, col, DIRECTION[direction1], map);
            search(row, col, DIRECTION[direction2], map);
        }

        public boolean isValid() {
            return count != 0;
        }
    }

    static class FourthCCTV implements CCTV {
        int row, col, count;
        int direction1, direction2, direction3;

        public FourthCCTV(int row, int col) {
            this.row = row;
            this.col = col;
            setCount();
            direction1 = 2;
            direction2 = 3;
            direction3 = 0;
        }

        public void setCount() {
            count = 4;
        }

        public void move() {
            if (count != 0) {
                direction1 = (direction1 + 1) % 4;
                direction2 = (direction2 + 1) % 4;
                direction3 = (direction3 + 1) % 4;
                count--;
            }
        }

        public void find(int[][] map) {
            search(row, col, DIRECTION[direction1], map);
            search(row, col, DIRECTION[direction2], map);
            search(row, col, DIRECTION[direction3], map);
        }

        public boolean isValid() {
            return count != 0;
        }
    }

    static class FifthCCTV implements CCTV {
        int row, col, count;
        int direction1, direction2, direction3, direction4;

        public FifthCCTV(int row, int col) {
            this.row = row;
            this.col = col;
            setCount();
            direction1 = 0;
            direction2 = 1;
            direction3 = 2;
            direction4 = 3;
        }

        public void setCount() {
            count = 1;
        }

        public void move() {
            if (count != 0) {
                count--;
            }
        }

        public void find(int[][] map) {
            search(row, col, DIRECTION[direction1], map);
            search(row, col, DIRECTION[direction2], map);
            search(row, col, DIRECTION[direction3], map);
            search(row, col, DIRECTION[direction4], map);
        }

        public boolean isValid() {
            return count != 0;
        }
    }
}