class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 1;
        int target = section[0];
        
        for (int i = 1; i < section.length; i++) {
            if (target + m - 1 < section[i]) {
                answer++;
                target = section[i];
            }
        }
        
        return answer;
    }
}