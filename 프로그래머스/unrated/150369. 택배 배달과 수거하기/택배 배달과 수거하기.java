import java.util.Stack;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
        Stack<Integer> deliverStack = new Stack<>();
        Stack<Integer> pickupStack = new Stack<>();
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < deliveries[i - 1]; j++) {
                deliverStack.add(i);
            }
            
            for (int j = 0; j < pickups[i - 1]; j++) {
                pickupStack.add(i);
            }
        }
        
        while (!deliverStack.isEmpty() && !pickupStack.isEmpty()) {
            int lastDeliverHouse = deliverStack.peek();
            int lastPickupHounse = pickupStack.peek();
            
            for (int i = 0; i < cap; i++) {
                if (!deliverStack.isEmpty()) deliverStack.pop();
                if (!pickupStack.isEmpty()) pickupStack.pop();
            }
            
            answer += Math.max(lastDeliverHouse, lastPickupHounse) * 2;
        }
        
        while (!deliverStack.isEmpty()) {
            int lastDeliverHouse = deliverStack.peek();
            
            for (int i = 0; i < cap; i++) {
                if (!deliverStack.isEmpty()) deliverStack.pop();
            }
            
            answer += lastDeliverHouse * 2;
        }
        
        while (!pickupStack.isEmpty()) {
            int lastPickupHounse = pickupStack.peek();
            
            for (int i = 0; i < cap; i++) {
                if (!pickupStack.isEmpty()) pickupStack.pop();
            }
            
            answer += lastPickupHounse * 2;
        }
        
        return answer;
    }
}