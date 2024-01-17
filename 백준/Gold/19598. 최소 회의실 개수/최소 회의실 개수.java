import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // step 1. 입력 값 정리
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        Meeting[] meetings = new Meeting[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            int start = Integer.parseInt(tokenizer.nextToken());
            int end = Integer.parseInt(tokenizer.nextToken());
            meetings[i] = new Meeting(start, end);
        }

        // step 2. Comparable 조건에 맞게 Meeting 정렬
        Arrays.sort(meetings);

        // step 3. 최소 회의실 개수를 선택
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();
        endTimes.offer(meetings[0].end);
        for (int i = 1; i < meetings.length; i++) {
            Meeting currentMeeting = meetings[i];

            // 가장 빠른 회의 종료 시간보다 현재 회의의 시작 시간이 뒤쪽에 있으면 이어서 진행할 수 있는 회의
            if (!endTimes.isEmpty() && endTimes.peek() <= currentMeeting.start) {
                endTimes.poll();
            }
            endTimes.offer(currentMeeting.end);
        }
        System.out.println(endTimes.size());
    }
}

class Meeting implements Comparable<Meeting> {
    int start, end;

    public Meeting(int start, int end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 회의 시작 시간을 기준으로 오름차순 정렬
     */
    @Override
    public int compareTo(Meeting meeting) {
        return this.start - meeting.start;
    }
}
