import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(reader.readLine());
        long roomNum = 1;
        if (n == 1) { // 1번방이면 그냥 1을 출력
            System.out.println(roomNum);
        } else {
            long minimumValueInRoom = 1;
            int productVal = 1;
            while (n > minimumValueInRoom) {
                roomNum++;
                minimumValueInRoom += (6 * productVal++);
            }
            System.out.println(roomNum);
        }
    }
}