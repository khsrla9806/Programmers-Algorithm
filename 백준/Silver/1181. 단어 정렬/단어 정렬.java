import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(reader.readLine());

        List<String> words = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String word = reader.readLine();
            if (words.contains(word)) {
                continue;
            }
            words.add(word);
        }

        Collections.sort(words, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                if (str1.length() == str2.length()) {
                    return str1.compareTo(str2);
                }
                return str1.length() - str2.length();
            }
        });

        words.forEach(word -> {
            System.out.println(word);
        });
    }
}