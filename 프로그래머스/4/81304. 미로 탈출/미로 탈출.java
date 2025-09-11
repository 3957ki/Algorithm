import java.util.*;

class Solution {
    private static final int INF = Integer.MAX_VALUE;
    private static Map<Integer, Integer> map;
    
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        
        List<Node>[] edges = new List[n+1];
        for(int i = 1; i <= n; i++){
            edges[i] = new ArrayList<>();
        }
        
        for(int[] road : roads){
            edges[road[0]].add(new Node(road[1], road[2], 0, false));
            edges[road[1]].add(new Node(road[0], road[2], 0, true));
        }
        
        map = new HashMap<>();
        int T = traps.length;
        for(int i = 0; i < T; i++){
            map.put(traps[i], i);
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        pq.add(new Node(start, 0, 0, false));
        boolean[][] visited = new boolean[n+1][1<<10];
        
        while(!pq.isEmpty()){
            Node now = pq.poll();
            
            if(visited[now.num][now.state]) continue;
            if(now.num == end) return now.cost;
            visited[now.num][now.state] = true;
            
            for(Node next : edges[now.num]){
                if(map.containsKey(next.num)){
                    boolean f = now.fliped != ((now.state & (1 << map.get(next.num))) > 0);
                    if(next.fliped != f) continue;
                    pq.add(new Node(next.num, 
                                        now.cost + next.cost, 
                                        now.state ^ (1 << map.get(next.num)), 
                                        (now.state & (1 << map.get(next.num))) == 0));
                }
                
                else{
                    boolean f = now.fliped;
                    if(next.fliped != f) continue;
                    pq.add(new Node(next.num, now.cost + next.cost, now.state, false));
                }
            }
            
        }
        
        return 0;
    }
    
    private class Node{
        int num;
        int cost;
        int state;
        boolean fliped;

        Node(int num, int cost, int state){
            this.num = num;
            this.cost = cost;
            this.state = state;
        }
        
        Node(int num, int cost, int state, boolean fliped){
            this.num = num;
            this.cost = cost;
            this.state = state;
            this.fliped = fliped;
        }
    }
}