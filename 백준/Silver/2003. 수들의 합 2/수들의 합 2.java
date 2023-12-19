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

            if (i > 0) {
                numbers[i] += numbers[i - 1];
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int result;
                if (i == 0) {
                    result = numbers[j];
                } else {
                    result = numbers[j] - numbers[i - 1];
                }
                if (result > M) {
                    break;
                }
                if (result == M) {
                    answer++;
                }
            }
        }
        System.out.println(answer);
    }
}
