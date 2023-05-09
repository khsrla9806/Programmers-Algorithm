import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(token.nextToken());
        int y = Integer.parseInt(token.nextToken()); // 행
        int x = Integer.parseInt(token.nextToken()); // 열
        int size = (int) Math.pow(2, n); // 한 변 사이즈
        search(size, y, x);
        System.out.println(answer);
    }

    public static void search(int size, int y, int x) {
        if (size == 1) return;

        if (y < size / 2 && x < size / 2) { // 1사분면
            search(size / 2, y, x);
        } else if (y < size / 2 && x >= size / 2) { // 2사분면
            answer += ((int) Math.pow(size, 2) / 4);
            search(size / 2, y, x - (size / 2));
        } else if (y >= size / 2 && x < size / 2) { // 3사분면
            answer += ((int) Math.pow(size, 2) / 4) * 2;
            search(size / 2, y - (size / 2), x);
        } else if (y >= size / 2 && x >= size / 2) { // 4사분면
            answer += ((int) Math.pow(size, 2) / 4) * 3;
            search(size / 2, y - (size / 2), x - (size / 2));
        }
    }
}