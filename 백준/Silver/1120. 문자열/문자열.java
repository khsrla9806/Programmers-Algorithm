import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(reader.readLine(), " ");
        char[] a = st.nextToken().toCharArray();
        char[] b = st.nextToken().toCharArray();
        int aLength = a.length;
        int bLength = b.length;
        int maxMatchCnt = 0;

        if (aLength == bLength) {
            for (int i = 0; i <aLength; i++) {
                if (a[i] == b[i]) {
                    maxMatchCnt++;
                }
            }
        } else {
            for (int i = 0; i <= (bLength - aLength); i++) {
                int matchCnt = 0;
                for (int j = 0; j < aLength; j++) {
                    if (a[j] == b[j + i]) {
                        matchCnt++;
                    }
                }
                maxMatchCnt = Math.max(maxMatchCnt, matchCnt);
            }
        }

        System.out.println(aLength - maxMatchCnt);
    }
}