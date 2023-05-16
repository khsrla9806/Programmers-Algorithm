import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> nameMap = new HashMap<>();
        String[] nameArr = new String[n + 1];
        for (int i = 1; i <= n; i++) {
            String name = br.readLine();
            nameMap.put(name, i);
            nameArr[i] = name;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String inputData = br.readLine();
            try {
                int number = Integer.parseInt(inputData);
                sb.append(nameArr[number]).append("\n");
            } catch (NumberFormatException e) {
                sb.append(nameMap.get(inputData)).append("\n");
            }
        }
        System.out.println(sb);
    }
}
