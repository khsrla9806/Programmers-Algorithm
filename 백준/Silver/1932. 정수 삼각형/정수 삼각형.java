import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] triangle = new int[n][];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[st.countTokens()];
            for (int j = 0; j < arr.length; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            triangle[i] = arr;
        }

        for (int i = 1; i < n; i++) {
            int[] before = triangle[i - 1];

            for (int j = 0; j < triangle[i].length; j++) {
                int left = j - 1;
                int right = j;
                int curr = triangle[i][j];
                int result = 0;

                if (left >= 0) {
                    result = Math.max(result, before[left] + curr);
                }

                if (right < before.length) {
                    result = Math.max(result, before[right] + curr);
                }
                triangle[i][j] = result;
            }
        }
        int[] result = triangle[n - 1];
        Arrays.sort(result);
        System.out.println(result[n - 1]);
    }
}
