class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        int lastDeliver = n;
        int lastPickup = n;
        
        while (lastDeliver >= 1 || lastPickup >= 1) {
            int load = 0;
            
            while (lastDeliver >= 1 && deliveries[lastDeliver - 1] == 0) {
                lastDeliver--;
            }
            while (lastPickup >= 1 && pickups[lastPickup - 1] == 0) {
                lastPickup--;
            }
            
            answer += Math.max(lastDeliver, lastPickup) * 2;
            
            while (lastDeliver >= 1 && load < cap) {
                load += deliveries[lastDeliver - 1];
                deliveries[lastDeliver - 1] = 0;
                lastDeliver--;
            }
            if (load > cap) {
                deliveries[lastDeliver++] = load - cap;
            }
            
            load = 0;
            while (lastPickup >= 1 && load < cap) {
                load += pickups[lastPickup - 1];
                pickups[lastPickup - 1] = 0;
                lastPickup--;
            }
            if (load > cap) {
                pickups[lastPickup++] = load - cap;
            }
        }
        
        return answer;
    }
}