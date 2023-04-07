import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int kim = Integer.parseInt(input[1]);
        int lim = Integer.parseInt(input[2]);
        int round = 0;

        while (kim != lim) {
            kim = (kim + 1) / 2;
            lim = (lim + 1) / 2;
            round++;
        }

        System.out.println(round);
    }
}