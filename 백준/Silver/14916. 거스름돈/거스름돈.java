import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int count2won = 0, count5won = 0;

        // 2원이 적을 수록 적은 수의 동전으로 거스름이 가능하다.
        while ((2 * count2won) <= n && (n - 2 * count2won) % 5 != 0) {
            count2won++;
        }
        count5won = (n - 2 * count2won) / 5;
        
        if ((2 * count2won) + (5 * count5won) != n) {
            System.out.println(-1);
            return;
        }
        System.out.println(count2won + count5won);
    }
}
