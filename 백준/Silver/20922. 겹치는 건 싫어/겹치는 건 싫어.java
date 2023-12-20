import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // step 1. process input data
        String[] firstLineInput = reader.readLine().split(" ");
        String[] secondLineInput = reader.readLine().split(" ");

        // step 2. declare variable
        int answer = 0;
        int length = 0;
        int N = Integer.parseInt(firstLineInput[0]);
        int K = Integer.parseInt(firstLineInput[1]);
        int[] numbers = Arrays.stream(secondLineInput).mapToInt(Integer::valueOf).toArray();
        int[] duplicatedCounts = new int[100001];

        // step 3. start two pointer
        int start = 0;
        int end = 0;

        while (end < N) {
            int number = numbers[end++];

            while (start < end && !isValid(duplicatedCounts, K, number)) {
                int tempNumber = numbers[start++];
                duplicatedCounts[tempNumber]--;
                length--;
            }
            duplicatedCounts[number]++;
            length++;
            answer = Math.max(answer, length);
        }
        System.out.println(answer);
    }

    private static boolean isValid(int[] duplicatedCounts, int K, int number) {
        return duplicatedCounts[number] + 1 <= K;
    }
}
