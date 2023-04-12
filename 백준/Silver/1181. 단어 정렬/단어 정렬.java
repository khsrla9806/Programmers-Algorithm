import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(reader.readLine());

        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = reader.readLine();
        }

        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                if (str1.length() == str2.length()) {
                    return str1.compareTo(str2);
                }
                return str1.length() - str2.length();
            }
        });

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            if (i == 0 || !words[i].equals(words[i - 1])) {
                builder.append(words[i]).append("\n");
            }
        }
        System.out.println(builder);
    }
}