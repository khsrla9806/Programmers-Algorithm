import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static boolean[] visited;
    static int answer = Integer.MAX_VALUE;
    static List<int[]> house;
    static List<int[]> chickenHouse;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputData = reader.readLine().split(" ");
        int n = Integer.parseInt(inputData[0]);
        int m = Integer.parseInt(inputData[1]);

        house = new ArrayList<>();
        chickenHouse = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            inputData = reader.readLine().split(" ");
            for (int j = 0; j < inputData.length; j++) {
                if (inputData[j].equals("1")) {
                    house.add(new int[]{i, j});
                } else if (inputData[j].equals("2")) {
                    chickenHouse.add(new int[]{i, j, 0});
                }
            }
        }

        // 치킨집을 최소 1개부터 최대 M개 뽑는 모든 경우의 수를 확인?
        visited = new boolean[chickenHouse.size()];
        for (int i = 1; i <= m; i++) {
            combination(0, chickenHouse.size(), i, i);
        }

        System.out.println(answer);
    }

    public static void combination(int start, int n, int r, int selectCnt) {
        if (r == 0) {
            findChickenDistance(selectCnt);
        }

        for (int i = start; i < n; i++) {
            visited[i] = true;
            combination(i + 1, n, r - 1, selectCnt);
            visited[i] = false;
        }
    }

    public static void findChickenDistance(int selectCnt) {
        int minChickenDistance = 0;
        int index = 0;
        int[][] selectedChickenHouse = new int[selectCnt][];
        for (int i = 0; i < visited.length; i++) {
            if (visited[i]) {
                selectedChickenHouse[index++] = chickenHouse.get(i);
            }

            if (index > selectCnt) break;
        }

        for (int[] houseCors : house) {
            int distance = Integer.MAX_VALUE;
            for (int[] chickenCors : selectedChickenHouse) {
                int temp = Math.abs(houseCors[0] - chickenCors[0]) + Math.abs(houseCors[1] - chickenCors[1]);
                distance = Math.min(distance, temp);
            }
            minChickenDistance += distance;
        }
        answer = Math.min(answer, minChickenDistance);
    }
}