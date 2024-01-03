import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int T = Integer.parseInt(reader.readLine());

        for (int t = 0; t < T; t++) {
            String pattern = reader.readLine();
            boolean isPossible = search(pattern);
            builder.append(isPossible ? "YES" : "NO").append("\n");
        }

        System.out.println(builder);
    }

    public static boolean search(String pattern) {
        if (pattern.length() == 1) {
            return true;
        }
        int centerIndex = pattern.length() / 2;
        int startIndex = 0;
        int endIndex = pattern.length() - 1;
        while (startIndex < centerIndex) {
            if (pattern.charAt(startIndex++) == pattern.charAt(endIndex--)) {
                return false;
            }
        }

        return search(pattern.substring(0, centerIndex))
                && search(pattern.substring(centerIndex + 1));
    }
}
