import java.io.*;
import java.util.*;

public class Main {
    static int[][] map;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] inputData = reader.readLine().split(" ");
        n = Integer.parseInt(inputData[0]);
        int m = Integer.parseInt(inputData[1]);

        map = new int[n + 1][n + 1];
        for (int i = 0; i < m; i++) {
            inputData = reader.readLine().split(" ");
            int firstPerson = Integer.parseInt(inputData[0]);
            int secondPerson = Integer.parseInt(inputData[1]);
            map[firstPerson][secondPerson] = 1;
            map[secondPerson][firstPerson] = 1;
        }

        PriorityQueue<Person> queue = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            Person person = new Person(i, 0);
            dfs(person, i, new boolean[n + 1]);
            queue.offer(person);
        }

         System.out.println(queue.poll().number);
    }

    public static void dfs(Person person, int number, boolean[] visited) {
        Queue<int[]> queue = new LinkedList<>();
        visited[number] = true;
        queue.offer(new int[]{number, 0});

        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            person.cabinCnt += data[1];

            for (int i = 1; i <= n; i++) {
                if (!visited[i] && map[data[0]][i] == 1) {
                    queue.offer(new int[]{i, data[1] + 1});
                    visited[i] = true;
                }
            }
        }
    }
}

class Person implements Comparable<Person> {
    int number;
    int cabinCnt;

    public Person(int number, int cabinCnt) {
        this.number = number;
        this.cabinCnt = cabinCnt;
    }

    @Override
    public int compareTo(Person that) {
        if (this.cabinCnt == that.cabinCnt) {
            return this.number - that.number;
        }
        return this.cabinCnt - that.cabinCnt;
    }
}