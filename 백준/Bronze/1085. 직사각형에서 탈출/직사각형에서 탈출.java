import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputData = reader.readLine().split(" ");
        int x = Integer.parseInt(inputData[0]);
        int y = Integer.parseInt(inputData[1]);
        int w = Integer.parseInt(inputData[2]);
        int h = Integer.parseInt(inputData[3]);

        int answer = Math.min(h - y, Math.min(y - 0, Math.min(w - x, x - 0)));
        System.out.println(answer);
    }
}