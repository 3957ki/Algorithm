import java.util.*;

class Solution {
    public int solution(int n, int[][] edges) {
        
        List<Integer>[] list = new List[n+1];
        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges){
            list[edge[0]].add(edge[1]);
            list[edge[1]].add(edge[0]);
        }
        
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(0, 1));
        
        Node root = null;
        while(!queue.isEmpty()){
            Node now = queue.poll();
            root = now;

            for(int next : list[now.num]){
                if(next == now.prev) continue;
                queue.add(new Node(now.num, next));
            }
        }
        
        queue.add(new Node(0, root.num));
        
        int answer = -1;
        int cnt = 0;
        while(!queue.isEmpty()){
            int L = queue.size();
            cnt = L;
            
            while(L-- > 0){
                Node now = queue.poll();
                root = now;
                
                for(int next : list[now.num]){
                    if(next == now.prev) continue;
                    queue.add(new Node(now.num, next));
                }
            }
            answer++;
        }
        
        if(cnt > 1) return answer;
        
        queue.add(new Node(0, root.num));
        
        answer = -1;
        cnt = 0;
        while(!queue.isEmpty()){
            int L = queue.size();
            cnt = L;
            
            while(L-- > 0){
                Node now = queue.poll();
                
                for(int next : list[now.num]){
                    if(next == now.prev) continue;
                    queue.add(new Node(now.num, next));
                }
            }
            answer++;
        }
        
        return cnt > 1 ? answer : answer - 1;
    }
    
    static class Node{
        int prev, num;
        
        Node(int prev, int num){
            this.prev = prev;
            this.num = num;
        }
    }
}