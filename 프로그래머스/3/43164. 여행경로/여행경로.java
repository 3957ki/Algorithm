import java.util.*;

class Solution {
    public String[] solution(String[][] tickets) {
        
        Arrays.sort(tickets, (o1, o2) -> o1[1].compareTo(o2[1]));
        
        int L = tickets.length;
        
        boolean[] visited = new boolean[L];        
        String[] answer = new String[L+1];
        
        answer[0] = "ICN";
        DFS("ICN", visited, answer, tickets, 1, L);
        
        return answer;
    }
    
    
    static boolean DFS(String now, boolean[] visited, String[] answer, String[][] tickets, int cnt, int limit){
        
        if(cnt > limit) return true;
            
        for(int i = 0; i < limit; i++){
            if(!visited[i] && tickets[i][0].equals(now)){
                visited[i] = true;
                answer[cnt] = tickets[i][1];
                if(DFS(tickets[i][1], visited, answer, tickets, cnt+1, limit)){
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}