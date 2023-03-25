import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int[][] cors = new int[26][2];
        boolean[][] bingo = new boolean[5][5];
        String[][] map = new String[5][5];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 5; i++) {
            String[] temp = br.readLine().split(" ");
            map[i] = temp;
            for (int j = 0; j < 5; j++) {
                int value = Integer.parseInt(temp[j]);
                cors[value] = new int[]{i, j};
            }
        }

        int count = 0;
        for (int i = 0; i < 5; i++) {
            String[] temp = br.readLine().split(" ");

            for (String str : temp) {
                count++;
                int value = Integer.parseInt(str);
                int[] valueCors = cors[value];
                bingo[valueCors[0]][valueCors[1]] = true;

                if (checkBingo(bingo)) {
                    System.out.println(count);
                    return;
                }
            }
        }
    }

    public static boolean checkBingo(boolean[][] bingo) {
        int bingoCnt = 0;
        boolean isBingo = true;

        // 가로 빙고 확인
        for (boolean[] b : bingo) {
            isBingo = true;
            for (int i = 0; i < 5; i++) {
                if (!b[i]) {
                    isBingo = false;
                }
            }
            if (isBingo) bingoCnt++;
        }

        // 세로 빙고 확인
        for (int i = 0; i < bingo[0].length; i++) {
            isBingo = true;
            for (int j = 0; j < bingo.length; j++) {
                if (!bingo[j][i]) {
                    isBingo = false;
                }
            }
            if (isBingo) bingoCnt++;
        }

        // 대각선 빙고 확인
        isBingo = true;
        for (int i = 0; i < 5; i++) {
            if (!bingo[i][i]) {
                isBingo = false;
                break;
            }
        }
        if (isBingo) bingoCnt++;

        isBingo = true;
        for (int i = 0; i < 5; i++) {
            if (!bingo[i][4 - i]) {
                isBingo = false;
                break;
            }
        }
        if (isBingo) bingoCnt++;

        return bingoCnt >= 3 ? true : false;
    }
}