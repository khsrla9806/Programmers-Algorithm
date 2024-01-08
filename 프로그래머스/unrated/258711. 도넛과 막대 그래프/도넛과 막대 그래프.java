import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        List<Integer>[] adjList = new ArrayList[1000001];
        int[][] inNout = new int[1000001][3];
        for(int[] edge : edges){
            int a = edge[0];
            int b = edge[1];
            inNout[a][0]++;
            inNout[b][1]++;
            inNout[a][2] = 1;
            inNout[b][2] = 1;
            // a->b연결관계 표시
            if(adjList[a] == null){
                adjList[a] = new ArrayList<Integer>();
                adjList[a].add(b);
            }
            else adjList[a].add(b);
        }
        int idx = 0;
        for(int i=0; i<1000001; i++){
            //들어오는 간선은 없지만 나가는 간선이 2이상이면 생성된 정점
            if(inNout[i][1] == 0 && inNout[i][0] >=2){
                idx = i;
                answer[0] = i;
            }
        }
        //생성된 정점과 연결된 정점들 in 간선 감소시켜주기
        int totalCount = 0; //전체 모양 그래프 개수
        for(int x : adjList[idx]){
            inNout[x][1]--;
            totalCount++;
        }
        inNout[idx][0] = 0;
        inNout[idx][1] = 0;
        inNout[idx][2] = 0;
        for(int i=0; i<1000001; i++){
            //막대 그래프
            if(inNout[i][1] == 0 && inNout[i][2] ==1)answer[2]++;

            //8자모양 그래프
            if(inNout[i][1] == 2 && inNout[i][0]==2) answer[3]++;
        }
        answer[1] = totalCount - (answer[2] + answer[3]);

        return answer;
    }
}