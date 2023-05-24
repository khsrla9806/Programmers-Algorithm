import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] numbers, leftDown, rightDown;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(dynamic());
    }

    public static int dynamic() {
        findLeftDown();
        findRightDown();
        int result = 0;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, leftDown[i] + rightDown[i]);
        }
        return result + 1;
    }

    public static void findLeftDown() {
        leftDown = new int[N];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (numbers[j] < numbers[i]) {
                    leftDown[i] = Math.max(leftDown[i], leftDown[j] + 1);
                }
            }
        }
    }

    public static void findRightDown() {
        rightDown = new int[N];
        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                if (numbers[j] < numbers[i]) {
                    rightDown[i] = Math.max(rightDown[i], rightDown[j] + 1);
                }
            }
        }
    }
}