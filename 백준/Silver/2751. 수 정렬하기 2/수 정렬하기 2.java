import java.io.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());
        TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        for (int i = 0; i < n; i++) {
            set.add(Integer.parseInt(reader.readLine()));
        }

        StringBuilder builder = new StringBuilder();
        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext()) {
            builder.append(iter.next()).append("\n");
        }
        System.out.println(builder);
    }
}