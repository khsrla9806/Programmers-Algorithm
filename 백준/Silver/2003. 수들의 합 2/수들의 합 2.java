import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputData = reader.readLine().split(" ");
        int N = Integer.parseInt(inputData[0]);
        int M = Integer.parseInt(inputData[1]);

        int[] numbers = new int[N];
        String[] inputNumbers = reader.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(inputNumbers[i]);
        }

        int answer = 0;
        int start = 0;
        int end = 0;
        int sum = 0;

        while (start < N) {
            while (end < N && sum < M) {
                sum += numbers[end];
                end++;
            }
            if (sum == M) {
                answer++;
            }
            sum -= numbers[start++];
        }

        System.out.println(answer);
    }
}
