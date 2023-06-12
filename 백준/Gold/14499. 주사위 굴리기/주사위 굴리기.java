import java.io.*;
import java.util.*;

public class Main {
    static final int EAST = 1, WEST = 2, SOUTH = 4, NORTH = 3;
    static int N, M, K;
    static int[][] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = getIntegerFromTokenizer(st);
        M = getIntegerFromTokenizer(st);
        int r = getIntegerFromTokenizer(st);
        int c = getIntegerFromTokenizer(st);
        K = getIntegerFromTokenizer(st);
        map = new int[N][M];
        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < M; col++) {
                map[row][col] = getIntegerFromTokenizer(st);
            }
        }
        StringBuilder builder = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        Dice dice = new Dice();
        for (int i = 0; i < K; i++) {
            int value = getIntegerFromTokenizer(st);
            int[] direction = getDirection(value);
            int nextR = r + direction[0];
            int nextC = c + direction[1];
            if (isValid(nextR, nextC)) {
                r = nextR;
                c = nextC;
                dice.move(value);
                int bottomValue = dice.getBottomValue();
                int mapValue = map[r][c];
                if (mapValue == 0) {
                    map[r][c] = bottomValue;
                } else {
                    dice.setBottomValue(mapValue);
                    map[r][c] = 0;
                }
                builder.append(dice.getHeadValue()).append("\n");
            }
        }
        System.out.println(builder);
    }

    public static boolean isValid(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

    public static int getIntegerFromTokenizer(StringTokenizer tokenizer) {
        return Integer.parseInt(tokenizer.nextToken());
    }

    public static int[] getDirection(int direction) {
        final int[][] DIRECTION = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        if (direction == EAST) {
            return DIRECTION[0];
        } else if (direction == WEST) {
            return DIRECTION[1];
        } else if (direction == SOUTH) {
            return DIRECTION[2];
        }
        return DIRECTION[3];
    }

    static class Dice {
        Head head;
        Bottom bottom;
        int[] value;

        public Dice() {
            head = new Head();
            bottom = new Bottom();
            value = new int[7];
        }

        public void move(int direction) {
            if (direction == EAST) {
                moveEast();
            } else if (direction == WEST) {
                moveWest();
            } else if (direction == SOUTH) {
                moveSouth();
            } else if (direction == NORTH) {
                moveNorth();
            }
        }

        private void moveEast() {
            int temp = head.right;
            head.right = head.number;
            head.number = head.left;
            head.left = bottom.number;
            bottom.number = temp;
        }

        private void moveWest() {
            int temp = head.left;
            head.left = head.number;
            head.number = head.right;
            head.right = bottom.number;
            bottom.number = temp;
        }

        private void moveSouth() {
            int temp = bottom.down;
            bottom.down = bottom.number;
            bottom.number = bottom.up;
            bottom.up = head.number;
            head.number = temp;
        }

        private void moveNorth() {
            int temp = bottom.up;
            bottom.up = bottom.number;
            bottom.number = bottom.down;
            bottom.down = head.number;
            head.number = temp;
        }

        public int getBottomValue() {
            return value[bottom.number];
        }

        public int getHeadValue() {
            return value[head.number];
        }

        public void setBottomValue(int value) {
            this.value[bottom.number] = value;
        }
    }

    static class Head {
        int number, left, right;

        public Head() {
            number = 1;
            left = 4;
            right = 3;
        }
    }

    static class Bottom {
        int number, up, down;

        public Bottom() {
            number = 6;
            up = 5;
            down = 2;
        }
    }
}