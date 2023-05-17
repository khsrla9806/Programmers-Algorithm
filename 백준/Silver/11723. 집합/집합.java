import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        HashSet<Integer> set = new HashSet<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int number = 0;
            if (st.hasMoreTokens()) number = Integer.parseInt(st.nextToken());
            if (command.equals("add")) {
                set.add(number);
            } else if (command.equals("remove")) {
                set.remove(number);
            } else if (command.equals("check")) {
                if (set.contains(number)) {
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else if (command.equals("toggle")) {
                if (set.contains(number)) {
                    set.remove(number);
                } else {
                    set.add(number);
                }
            } else if (command.equals("all")) {
                set.clear();
                for (int j = 1; j <= 20; j++) {
                    set.add(j);
                }
            } else {
                set.clear();
            }
        }
        System.out.println(sb);
    }
}
