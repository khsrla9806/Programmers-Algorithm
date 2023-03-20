import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        List<Room> reserve = new ArrayList<>();
        
        Arrays.sort(book_time, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return timeToMinute(o1[0]) - timeToMinute(o2[0]);
            }
        });
        
        for (int i = 0; i < book_time.length; i++) {
            boolean reserveComplete = false;
            int startTime = timeToMinute(book_time[i][0]);
            int endTime = timeToMinute(book_time[i][1]);
            
            if (reserve.size() == 0) {
                reserve.add(new Room(endTime));
                continue;
            }
            
            for (Room room : reserve) {
                if (room.isEmpty(startTime)) {
                    room.endTime = endTime;
                    reserveComplete = true;
                    break;
                }
            }
            
            if (!reserveComplete) {
                reserve.add(new Room(endTime));
            }
        }
        
        return reserve.size();
    }
    
    public int timeToMinute(String time) {
        StringTokenizer tokenizer = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(tokenizer.nextToken());
        int minute = Integer.parseInt(tokenizer.nextToken());
        
        return hour * 60 + minute;
    }
}

class Room {
    int endTime;
    
    public Room(int endTime) {
        this.endTime = endTime;
    }
    
    public boolean isEmpty(int startTime) {
        if (startTime >= endTime + 10) {
            return true;
        }
        return false;
    }
}