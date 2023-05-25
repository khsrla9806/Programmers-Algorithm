import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringBuilder builder = new StringBuilder();
            builder.append(br.readLine());
            String str = builder.toString();
            if (str.equals("0")) break;
            String reverseStr = builder.reverse().toString();
            sb.append(str.equals(reverseStr) ? "yes" : "no").append("\n");
        }
        System.out.println(sb);
    }
}