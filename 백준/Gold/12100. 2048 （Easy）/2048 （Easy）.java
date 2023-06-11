import java.io.*;
import java.util.*;

public class Main {
    static int N, answer;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int row = 0; row < N; row++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;
        find(count, map);
        System.out.println(answer);
    }

    public static void find(int count, int[][] inputMap) {
        if (count == 5) {
            int result = 0;
            for (int row = 0; row < N; row++) {
                for (int col = 0; col < N; col++) {
                    result = Math.max(result, inputMap[row][col]);
                }
            }
            answer = Math.max(answer, result);
            return;
        }

        find(count + 1, rightOrLeftWithCode("right", inputMap));
        find(count + 1, rightOrLeftWithCode("left", inputMap));
        find(count + 1, upOrDownWithCode("up", inputMap));
        find(count + 1, upOrDownWithCode("down", inputMap));
    }

    public static int[][] rightOrLeftWithCode(String code, int[][] inputMap) {
        int[][] result = new int[N][N];
        int indexAdd = code.equals("right") ? -1 : 1;
        LinkedList<Integer> numbers = new LinkedList<>();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (code.equals("right")) {
                    numbers.addLast(inputMap[row][col]);
                } else if (code.equals("left")) {
                    numbers.addFirst(inputMap[row][col]);
                }
            }
            int index = code.equals("right") ? N - 1 : 0;
            while (!numbers.isEmpty()) {
                int value = numbers.pollLast();
                if (value == 0) continue;
                if (result[row][index] == value) {
                    result[row][index] = value * 2;
                    index += indexAdd;
                } else {
                    if (result[row][index] == 0) {
                        result[row][index] = value;
                    } else {
                        index += indexAdd;
                        result[row][index] = value;
                    }
                }
            }
        }
        return result;
    }

    public static int[][] upOrDownWithCode(String code, int[][] inputMap) {
        int[][] result = new int[N][N];
        int indexAdd = code.equals("down") ? -1 : 1;
        LinkedList<Integer> numbers = new LinkedList<>();
        for (int col = 0; col < N; col++) {
            for (int row = 0; row < N; row++) {
                if (code.equals("down")) {
                    numbers.addLast(inputMap[row][col]);
                } else {
                    numbers.addFirst(inputMap[row][col]);
                }
            }
            int index = code.equals("down") ? N - 1 : 0;
            while (!numbers.isEmpty()) {
                int value = numbers.pollLast();
                if (value == 0) continue;
                if (result[index][col] == value) {
                    result[index][col] = value * 2;
                    index += indexAdd;
                } else {
                    if (result[index][col] == 0) {
                        result[index][col] = value;
                    } else {
                        index += indexAdd;
                        result[index][col] = value;
                    }
                }
            }
        }
        return result;
    }
}