import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Person[] people = new Person[n + 1];
        for (int i = 1; i <= n; i++) {
            people[i] = new Person(i);
        }

        // set targets
        String[] targets = reader.readLine().split(" ");
        int firstTarget = Integer.parseInt(targets[0]);
        int secondTarget = Integer.parseInt(targets[1]);

        // set relationship
        int m = Integer.parseInt(reader.readLine());
        for (int i = 0; i < m; i++) {
            String[] relationship = reader.readLine().split(" ");
            int parent = Integer.parseInt(relationship[0]);
            int child = Integer.parseInt(relationship[1]);
            people[parent].children.add(people[child]);
            people[child].parent = people[parent];
        }

        boolean[] visited = new boolean[n + 1];
        // search
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{firstTarget, 0});
        visited[firstTarget] = true;

        int answer = -1;
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            if (temp[0] == secondTarget) {
                answer = temp[1];
                break;
            }
            Person tempPerson = people[temp[0]];

            if (!tempPerson.isRoot() && !visited[tempPerson.parent.me]) {
                queue.add(new int[]{tempPerson.parent.me, temp[1] + 1});
                visited[tempPerson.parent.me] = true;
            }

            if (!tempPerson.isEmptyChildren()) {
                List<Person> children = tempPerson.children;
                for (Person person : children) {
                    if (!visited[person.me]) {
                        queue.add(new int[]{person.me, temp[1] + 1});
                    }
                }
            }
        }

        System.out.println(answer);
    }

    public void search() {

    }
}

class Person {
    int me;
    Person parent;
    List<Person> children;

    public Person(int me) {
        this.me = me;
        children = new ArrayList<>();
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isEmptyChildren() {
        return children.isEmpty();
    }
}
