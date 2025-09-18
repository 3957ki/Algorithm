import java.util.*;

class Solution {
    
    private List<Integer>[] edges;
    private int answer;
    
    public int solution(int[] info, int[][] edges) {
        
        int N = info.length;
        
        this.edges = new List[N];
        answer = 0;
        
        for(int i = 0; i < N; i++){
            this.edges[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges){
            this.edges[edge[0]].add(edge[1]);
        }
        
        List<Integer> list = new ArrayList<>();
        
        DFS(0, list, info, 1, 1);
        
        return answer;
    }
    
    private void DFS(int now, List<Integer> list, int[] info, int status, int sum){
        if(status <= 0) return;
        
        answer = Math.max(answer, sum);

        list.addAll(edges[now]);
        list.remove(Integer.valueOf(now));
        
        for(int next : list){
            List<Integer> newList = new ArrayList<>(list);
            if(info[next] == 0){
                DFS(next, newList, info, status + 1, sum + 1);
            }
            else{
                DFS(next, newList, info, status - 1, sum);
            }
        }
        
    }
}