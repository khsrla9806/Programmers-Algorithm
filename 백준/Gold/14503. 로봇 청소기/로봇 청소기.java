import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // step 1. 입력 값을 정리
        String[] NM = reader.readLine().split(" ");
        int N = Integer.parseInt(NM[0]);
        int M = Integer.parseInt(NM[1]);

        String[] robotInfo = reader.readLine().split(" ");
        int[] robotInitCors = {Integer.parseInt(robotInfo[0]), Integer.parseInt(robotInfo[1])};
        Robot robot = new Robot(robotInitCors, Integer.parseInt(robotInfo[2]));

        int[][] room = new int[N][M];
        for (int r = 0; r < N; r++) {
            String[] temp = reader.readLine().split(" ");
            for (int c = 0; c < M; c++) {
                room[r][c] = Integer.parseInt(temp[c]);
            }
        }

        // step 2. 로봇 청소 시작
        robot.cleanRoom(room);
        System.out.println(robot.cleanRoomCount);
    }
}

class Robot {
    private static final int[][] DIRECTION = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 북, 동, 남, 서
    int cleanRoomCount;
    int direction;
    int curRow, curCol;

    public Robot(int[] cors, int direction) {
        this.direction = direction;
        this.curRow = cors[0];
        this.curCol = cors[1];
    }

    public void cleanRoom(int[][] room) {
        // 현재 칸이 청소 가능하다면
        if (room[curRow][curCol] == 0) {
            room[curRow][curCol] = -1;
            cleanRoomCount++;
        }

        int backDirection = getBackDirection();
        int backRow = curRow + DIRECTION[backDirection][0];
        int backCol = curCol + DIRECTION[backDirection][1];

        // 주변에 청소 가능한 공간이 있는 경우
        if (isCleanablePlace(room)) {
            rotateReverse();

            // 현재 위치에서 앞에 청소되지 않았으면 한칸 전진
            int frontRow = curRow + DIRECTION[direction][0];
            int frontCol = curCol + DIRECTION[direction][1];
            if (isValidCors(frontRow, frontCol, room) && room[frontRow][frontCol] == 0) {
                curRow = frontRow;
                curCol = frontCol;
            }
            cleanRoom(room);
        } else if (isValidCors(backRow, backCol, room) && room[backRow][backCol] != 1) {
            curRow = backRow;
            curCol = backCol;
            cleanRoom(room);
        }
    }

    private boolean isCleanablePlace(int[][] room) {
        for (int[] d : DIRECTION) {
            int nextRow = curRow + d[0];
            int nextCol = curCol + d[1];
            if (isValidCors(nextRow, nextCol, room) && room[nextRow][nextCol] == 0) {
                return true;
            }
        }
        return false;
    }

    private boolean isValidCors(int row, int col, int[][] room) {
        return row >= 0 && row < room.length && col >= 0 && col < room[0].length;
    }

    private void rotateReverse() {
        direction--;
        if (direction < 0) {
            direction = 3;
        }
    }

    private int getBackDirection() {
        return (direction + 2) % 4;
    }
}
