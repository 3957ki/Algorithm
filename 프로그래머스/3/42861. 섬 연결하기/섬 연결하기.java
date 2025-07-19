import java.util.*;

class Solution {
    public int solution(int n, int[][] costs) {
        
        List<Node>[] edges = new List[n];
        for(int i = 0; i < n; i++){
            edges[i] = new ArrayList<>();
        }
        
        for(int[] now : costs){
            edges[now[0]].add(new Node(now[1], now[2]));
            edges[now[1]].add(new Node(now[0], now[2]));
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(0, 0));
        boolean[] visited = new boolean[n];
        int answer = 0;
        int cnt = 0;
        
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(visited[now.num]) continue;
            visited[now.num] = true;
            answer += now.cost;
            if(++cnt == n) break;
            
            for(Node next : edges[now.num]){
                if(visited[next.num]) continue;
                pq.add(next);
            }
            
        }
        
        return answer;
    }
    
    static class Node{
        int num, cost;
        Node(int num, int cost){
            this.num = num;
            this.cost = cost;
        }
    }
}