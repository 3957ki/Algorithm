import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        
        List<Node>[] edges = new List[n+1];
        for(int i = 1; i <= n; i++){
            edges[i] = new ArrayList<>();
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        int[] arr = new int[n+1];
        int[] dst = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(dst, Integer.MAX_VALUE);
        
        for(int num : gates){
            pq.add(new Node(num, 0));
            arr[num] = 1;
        }
        for(int num : summits) arr[num] = 2;
        
        for(int[] path : paths){
            edges[path[0]].add(new Node(path[1], path[2]));
            edges[path[1]].add(new Node(path[0], path[2]));
        }
        
        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.num]) continue;
            visited[now.num] = true;
            
            if(arr[now.num] == 2) continue;

            for(Node next : edges[now.num]){
                if(visited[next.num]) continue;
                int cost = Math.max(now.cost, next.cost);
                if(dst[next.num] > cost){
                    dst[next.num] = cost;
                    pq.add(new Node(next.num, cost));
                }
            }
        }
        
        int answer_num = 0;
        int min = 10000001;
        for(int i = 1; i <= n; i++){
            if(arr[i] != 2) continue;
            System.out.println(arr[i] + " " + dst[i]);
            if(min > dst[i]){
                min = dst[i];
                answer_num = i;
            }
            else if(min == dst[i]) answer_num = Math.min(answer_num, i);
        }
        
        int[] answer = {answer_num, min};
        return answer;
    }
    
    private class Node{
        int num, cost;
        Node(int num, int cost){
            this.num = num;
            this.cost = cost;
        }
    }
}