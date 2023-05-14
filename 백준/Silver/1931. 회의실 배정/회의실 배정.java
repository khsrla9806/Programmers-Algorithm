import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Meeting> meetings = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings.offer(new Meeting(start, end));
        }
        
        Meeting prevMeeting = meetings.poll();
        int answer = 1; // 가장 처음 회의 일정은 추가되기 때문에 1부터 시작
        while (!meetings.isEmpty()) {
            Meeting meeting = meetings.poll();
            // 현재 회의 시작 시간이 이전 회의 일정의 종료 시간과 동일하거나 크면 정답에 추가
            if (prevMeeting.end <= meeting.start) {
                answer++;
                // 현재 회의를 이전 회의로 지정
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
        // 종료 시간이 동일하다면 시작 시점이 빠른 순으로 정렬
        if (this.end == obj.end) {
            return this.start - obj.start;
        }
        // 종료 시점이 빠른 순으로 회의 일정을 정렬
        return this.end - obj.end;
    }
}