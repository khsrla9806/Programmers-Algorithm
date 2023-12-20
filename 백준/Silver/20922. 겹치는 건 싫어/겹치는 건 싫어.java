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
        Map<Integer, Integer> duplicatedCounts = new HashMap<>();

        // step 3. start two pointer
        int start = 0;
        int end = 0;

        while (end < N) {
            int number = numbers[end++];

            while (start < end && !isValid(duplicatedCounts, K, number)) {
                int tempNumber = numbers[start++];
                int tempDuplicatedCount = duplicatedCounts.get(tempNumber);
                if (tempDuplicatedCount - 1 == 0) {
                    duplicatedCounts.remove(tempNumber);
                } else {
                    duplicatedCounts.put(tempNumber, tempDuplicatedCount - 1);
                }
                length--;
            }
            duplicatedCounts.put(number, duplicatedCounts.getOrDefault(number, 0) + 1);
            length++;
            answer = Math.max(answer, length);
        }
        System.out.println(answer);
    }

    private static boolean isValid(Map<Integer, Integer> duplicatedCounts, int K, int number) {
        return duplicatedCounts.getOrDefault(number, 0) + 1 <= K;
    }
}
