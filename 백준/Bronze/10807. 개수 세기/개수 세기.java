import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(br.readLine());
        int answer = 0;
        while (st.hasMoreTokens()) {
            if (Integer.parseInt(st.nextToken()) == v) answer++;
        }
        System.out.println(answer);
    }
}