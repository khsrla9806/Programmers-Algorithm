import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int T = scan.nextInt();
        
        for (int i = 0; i < T; i++) {
            int r = scan.nextInt();
            String s = scan.next();
            String ans = "";
            
            for (int j = 0; j < s.length(); j++) {
                for (int k = 0; k < r; k++) {
                    ans += s.charAt(j);
                }
            }
            System.out.println(ans);
        }
    }
}