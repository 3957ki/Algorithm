class Solution {
    
    static boolean[] visited;
    static int answer = 0;
    
    public int solution(int n, int[][] computers) {
        
        visited = new boolean[n];
        
        for(int i = 0; i < n; i++){
            if(visited[i]) continue;
            visited[i] = true;
            answer++;
            DFS(i, n, computers);
        }
        
        return answer;
    }
    
    static void DFS(int now, int n, int[][] computers){
        
        for(int i = 0; i < n; i++){
            if(!visited[i] && computers[now][i] == 1){
                visited[i] = true;
                DFS(i, n, computers);
            }
        }
        
    }
}