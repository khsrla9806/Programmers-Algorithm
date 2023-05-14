import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ArrayList<Meeting> meetings = new ArrayList<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings.add(new Meeting(start, end));
        }
        Collections.sort(meetings);

        int answer = 1;
        Meeting prevMeeting = meetings.get(0);
        for (int i = 1; i < n; i++) {
            Meeting meeting = meetings.get(i);

            if (prevMeeting.end <= meeting.start) {
                answer++;
                prevMeeting = meeting;
            }
        }

        System.out.println(answer);
    }
}

class Meeting implements Comparable<Meeting> {
    int start;
    int end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Meeting obj) {
        if (this.end == obj.end) {
            return this.start - obj.start;
        }
        return this.end - obj.end;
    }
}