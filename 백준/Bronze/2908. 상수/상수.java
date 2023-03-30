import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int x = scan.nextInt();
        int y = scan.nextInt();
        int newX = (x % 10) * 100 + (x / 10 % 10) * 10 + (x / 100);
        int newY = (y % 10) * 100 + (y / 10 % 10) * 10 + (y / 100);
        
        int answer = newX > newY ? newX : newY;
        
        System.out.println(answer);
    }
}