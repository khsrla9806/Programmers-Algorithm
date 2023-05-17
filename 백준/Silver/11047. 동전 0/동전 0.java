import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int money = Integer.parseInt(st.nextToken());
        int[] moneys = new int[n];
        for (int i = 0; i < n; i++) {
            moneys[n - i - 1] = Integer.parseInt(br.readLine());
        }
        int answer = 0;
        int index = 0;
        while (money > 0) {
            if (money < moneys[index]) {
                index++;
            } else {
                answer += (money / moneys[index]);
                money %= moneys[index];
            }
        }
        System.out.println(answer);
    }
}
