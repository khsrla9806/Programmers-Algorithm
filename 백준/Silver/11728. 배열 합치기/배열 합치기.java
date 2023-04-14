import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputData = reader.readLine().split(" ");
        int n = Integer.parseInt(inputData[0]);
        int m = Integer.parseInt(inputData[1]);

        List<Integer> numbers = new ArrayList<>();
        inputData = reader.readLine().split(" ");
        for (String data : inputData) {
            numbers.add(Integer.parseInt(data));
        }
        inputData = reader.readLine().split(" ");
        for (String data : inputData) {
            numbers.add(Integer.parseInt(data));
        }
        Collections.sort(numbers);

        StringBuilder builder = new StringBuilder();
        numbers.forEach(data -> {
            builder.append(data).append(" ");
        });
        System.out.println(builder);
    }
}