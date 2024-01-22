import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;

public class Main {
    static int[] expectedNumberCount;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        expectedNumberCount = new int[1000];

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            String target = tokenizer.nextToken();
            int strike = Integer.parseInt(tokenizer.nextToken());
            int ball = Integer.parseInt(tokenizer.nextToken());
            checkNumbers(target, new int[]{strike, ball});
        }

        int answer = 0;
        for (int i = 123; i < 1000; i++) { // 123이 나올 수 있는 가장 작은 숫자의 시작
            if (expectedNumberCount[i] == N) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    /**
     * 3글자 숫자를 받아 000 ~ 999까지 순회하며 조건에 맞는 것을 모두 체크
     * condition[0] = 스트라이크, condition[1] = 볼
     */
    private static void checkNumbers(String target, int[] condition) {
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                if (i == j) continue;
                for (int k = 1; k < 10; k++) {
                    if (i == k || j == k) continue;
                    int[] result = getResult(target, i, j, k);

                    if (condition[0] == result[0] && condition[1] == result[1]) {
                        int index = (i * 100) + (j * 10) + k;
                        expectedNumberCount[index]++;
                    }
                }
            }
        }
    }

    private static int[] getResult(String target, int first, int second, int third) {
        int[] result = new int[2];
        for (int index = 0; index < 3; index++) {
            int number = target.charAt(index) - '0';

            // 스트라이크 체크
            if ((index == 0 && number == first)
                    || (index == 1 && number == second)
                    || (index == 2 && number == third)) {
                result[0]++;
                continue;
            }

            // 볼 체크
            if (number == first || number == second || number == third) {
                result[1]++;
            }
        }

        return result;
    }
}
