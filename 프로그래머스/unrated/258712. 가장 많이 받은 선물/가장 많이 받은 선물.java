import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        // step 1. 필요한 데이터 초기화 작업
        int[][] histories = new int[friends.length][friends.length];
        int[] score = new int[friends.length];
        
        Map<String, Integer> friendIds = new HashMap<>();
        int idx = 0;
        for (String friend : friends) {
            friendIds.put(friend, idx);
            histories[idx][idx] = -1;
            idx++;
        }
        
        // step 2. 선물 주고받은 기록 데이터 적용
        for (String gift : gifts) {
            String[] temp = gift.split(" ");
            int senderIdx = friendIds.get(temp[0]);
            int receiverIdx = friendIds.get(temp[1]);
            
            // histories[받은 사람][보낸 사람]
            histories[receiverIdx][senderIdx]++;
        }
        
        // step 3. 선물 지수 계산
        for (int i = 0; i < idx; i++) {
            for (int j = 0; j < idx; j++) {
                // i가 보낸건 더하고, 받은 건 뺌
                if (histories[i][j] == -1) continue;
                score[i] += (histories[j][i] - histories[i][j]);
            }
        }
        
        // step 4. 결과 도출
        int[] result = new int[idx];
        
        for (int i = 0; i < idx; i++) {
            for (int j = 0; j < idx; j++) {
                if (histories[i][j] == -1) continue;
                // i가 j한테 보낸 선물 수
                int iToJ = histories[j][i];
                // j가 i한테 보낸 선물 수
                int jToI = histories[i][j];
                
                if (iToJ > jToI) {
                    result[i]++;
                } else if (iToJ < jToI) {
                    result[j]++;
                } else if (score[i] > score[j]) {
                    result[i]++;
                } else if (score[i] < score[j]) {
                    result[j]++;
                }
                
                // i와 j의 관계 확인 끝
                histories[j][i] = -1;
                histories[i][j] = -1;
            }
        }
        
        Arrays.sort(result);
        
        return result[idx - 1];
    }
}