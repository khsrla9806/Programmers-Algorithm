import java.io.*;
import java.util.*;

public class Main {
    static int id = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        PriorityQueue<Member> members = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            members.offer(new Member(++id, Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        StringBuilder builder = new StringBuilder();
        while (!members.isEmpty()) {
            builder.append(members.poll());
        }

        System.out.println(builder);
    }
}

class Member implements Comparable<Member> {
    int id;
    int age;
    String name;

    public Member(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Member obj) {
        if (this.age == obj.age) {
            return this.id - obj.id;
        }
        return this.age - obj.age;
    }

    @Override
    public String toString() {
        return this.age + " " + this.name + "\n";
    }
}