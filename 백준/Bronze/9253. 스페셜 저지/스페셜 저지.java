import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstStr = reader.readLine();
        String secondStr = reader.readLine();
        String printedStr = reader.readLine();

        if (firstStr.contains(printedStr) && secondStr.contains(printedStr)) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }
}
