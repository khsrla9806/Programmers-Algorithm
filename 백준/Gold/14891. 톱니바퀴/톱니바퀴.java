import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<char[]> wheels = new ArrayList<>();
        int[] wheelHead = new int[]{0, 0, 0, 0};
        char[][] leftAndRight = new char[4][2];

        for (int i = 0; i < 4; i++) {
            char[] inputData = reader.readLine().toCharArray();
            wheels.add(inputData);
            leftAndRight[i][0] = inputData[6];
            leftAndRight[i][1] = inputData[2];
        }

        int turn = Integer.parseInt(reader.readLine());
        for (int i = 0; i < turn; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int wheelNumber = Integer.parseInt(tokenizer.nextToken()) - 1; // Index
            int turnDirection = Integer.parseInt(tokenizer.nextToken());

            boolean[] isTurn = new boolean[4];
            int[] nextTurnDirection = new int[4];

            // 현재 톱니 회전 반영
            isTurn[wheelNumber] = true;
            nextTurnDirection[wheelNumber] = turnDirection;

            // 톱니 왼쪽 탐색
            int direction = turnDirection;
            for (int j = wheelNumber - 1; j >= 0; j--) {
                direction *= -1;
                if (leftAndRight[j + 1][0] != leftAndRight[j][1]) {
                    isTurn[j] = true;
                    nextTurnDirection[j] = direction;
                } else {
                    break;
                }
            }
            // 톱니 오른쪽 탐색
            direction = turnDirection;
            for (int j = wheelNumber + 1; j < 4; j++) {
                direction *= -1;
                if (leftAndRight[j - 1][1] != leftAndRight[j][0]) {
                    isTurn[j] = true;
                    nextTurnDirection[j] = direction;
                } else {
                    break;
                }
            }

            // 톱니 Head와 Left, Right 갱신 작업
            for (int j = 0; j < 4; j++) {
                if (isTurn[j]) {
                    if (nextTurnDirection[j] == -1) {
                        wheelHead[j] = (wheelHead[j] + 1) % 8;
                    } else if (nextTurnDirection[j] == 1) {
                        wheelHead[j] = (wheelHead[j] + 7) % 8;
                    }
                    leftAndRight[j][0] = wheels.get(j)[(wheelHead[j] + 6) % 8];
                    leftAndRight[j][1] = wheels.get(j)[(wheelHead[j] + 2) % 8];
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < 4; i++) {
            if (wheels.get(i)[wheelHead[i]] == '1') {
                answer += Math.pow(2, i);
            }
        }
        System.out.println(answer);
    }
}