import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static final String[] vowels = {"a", "e", "i", "o", "u"};
    static StringBuilder answerBuilder;
    static char[] characters;
    static boolean[] charactersVisited;
    static int L, C;

    public static void main(String[] args) throws IOException {
        // Step 1. 입력 값을 초기화
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] LC = reader.readLine().split(" ");
        L = Integer.parseInt(LC[0]);
        C = Integer.parseInt(LC[1]);
        characters = reader.readLine().replace(" ", "").toCharArray();
        charactersVisited = new boolean[C];
        Arrays.sort(characters);

        // Step 2. C개 중에서 L개를 선택해야 한다.
        answerBuilder = new StringBuilder();
        combination(0, L);
        System.out.println(answerBuilder);
    }

    private static void combination(int start, int r) {
        if (r == 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < C; i++) {
                if (charactersVisited[i]) {
                    builder.append(characters[i]);
                }
            }
            String code = builder.toString();
            if (isValidCode(code)) {
                answerBuilder.append(code).append("\n");
            }
            return;
        }

        for (int i = start; i < C; i++) {
            charactersVisited[i] = true;
            combination(i + 1, r - 1);
            charactersVisited[i] = false;
        }
    }

    private static boolean isValidCode(String code) {
        int vowelCount = 0;
        for (String vowel : vowels) {
            if (code.contains(vowel)) {
                vowelCount++;
            }
        }

        return vowelCount >= 1 && code.length() - vowelCount >= 2;
    }
}
