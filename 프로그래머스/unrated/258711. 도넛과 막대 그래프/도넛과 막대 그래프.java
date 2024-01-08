import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        // step 1. 그래프 간선 정보 설정 및 In/Out Count 정보 설정
        Node[] map = new Node[1000001];
        
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if (map[from] == null) map[from] = new Node(from);
            if (map[to] == null) map[to] = new Node(to);
            map[from].outNodes.add(map[to]);
            map[from].outCount++;
            map[to].inCount++;
        }
        
        // step 2. 생성된 정점 찾기
        Node createdNode = null;
        for (Node node : map) {
            if (node != null && node.inCount == 0 && node.outCount >= 2) {
                answer[0] = node.number;
                createdNode = node;
                break;
            }
        }
        
        // step 3. 생성된 정점에서 부터 그래프 탐색 시작
        int totalGraphCount = createdNode.outCount;
        
        for (Node node : createdNode.outNodes) {
            search(node, answer);
        }
        
        // step 4. 도넛 그래프 = 전체 그래프 수 - (막대 그래프 + 8자 그래프)
        answer[1] = totalGraphCount - (answer[2] + answer[3]);
        
        return answer;
    }
    
    private void search(Node node, int[] answer) {
        node.isVisited = true;
        
        // 해당 노드가 8자 그래프인 경우
        if (node.inCount >= 2 && node.outCount == 2) {
            answer[3]++;
            return;
        }
        
        Node nextNode = node.getNextNode();
        if (nextNode == null) {
            // 막대 그래프인 경우 = 마지막 노드의 outCount가 0일때
            if (node.outCount == 0) {
                answer[2]++;
            }
            return;
        }
        
        search(nextNode, answer);
    }
}

class Node {
    int number;
    int outCount;
    int inCount;
    boolean isVisited;
    List<Node> outNodes;
    
    public Node(int number) {
        this.number = number;
        this.outNodes = new ArrayList<>();
    }
    
    public Node getNextNode() {
        for (Node node : outNodes) {
            if (!node.isVisited) {
                return node;
            }
        }
        return null;
    }
}