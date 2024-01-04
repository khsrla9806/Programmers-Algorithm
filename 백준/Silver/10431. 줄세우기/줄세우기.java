import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder builder = new StringBuilder();
        int P = Integer.parseInt(reader.readLine());

        for (int i = 0; i < P; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine(), " ");
            builder.append(tokenizer.nextToken()).append(" ");

            LinkedList<Integer> students = new LinkedList<>();
            while (tokenizer.hasMoreTokens()) {
                students.add(Integer.parseInt(tokenizer.nextToken()));
            }
            builder.append(sortLine(students)).append("\n");
        }

        System.out.println(builder);
    }

    private static int sortLine(LinkedList<Integer> students) {
        int answer = 0;
        LinkedList<Integer> sortedStudents = new LinkedList<>();
        while (!students.isEmpty()) {
            int student = students.pollFirst();
            int smallerCount = findCountSmallerThanMe(sortedStudents, student);
            answer += sortedStudents.size() - smallerCount;
            sortedStudents.add(smallerCount, student);
        }

        return answer;
    }

    private static int findCountSmallerThanMe(LinkedList<Integer> students, int me) {
        int count = 0;
        for (int student : students) {
            if (student > me) break;
            if (student < me) {
                count++;
            }
        }
        return count;
    }
}
