import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputData = reader.readLine().split(" ");
        int k = Integer.parseInt(inputData[0]);
        int n = Integer.parseInt(inputData[1]);

        int[] numbers = new int[k];
        long maxDivider = Integer.MIN_VALUE;
        for (int i = 0; i < k; i++) {
            int number = Integer.parseInt(reader.readLine());
            maxDivider = Math.max(maxDivider, number);
            numbers[i] = number;
        }

        // 0으로 나누어지는 경우를 대비하여 1을 더해줌.
        maxDivider += 1;
        long minDivider = 0;
        long midDivider = 0;

        while (minDivider < maxDivider) {

            midDivider = (minDivider + maxDivider) / 2;
            int count = 0;
            for (int num : numbers) {
                count += (num / midDivider);
            }

            if (count >= n) {
                minDivider = midDivider + 1;
            } else {
                maxDivider = midDivider;
            }
        }

        System.out.println(minDivider - 1);
    }
}