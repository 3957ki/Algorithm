import java.util.*;

class Solution {
    private Map<String, Integer> map = new HashMap<>();
    private int[][] arr = {{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    private int[][] list;
    private int answer;
    
    Solution(){
        map.put("diamond", 0);
        map.put("iron", 1);
        map.put("stone", 2);
    }
    
    public int solution(int[] picks, String[] minerals) {
        answer = Integer.MAX_VALUE;
        int N = Math.min(minerals.length, 5 * (picks[0] + picks[1] + picks[2]));
        int L = (N + 4) / 5;
        
        list = new int[L][3];
        
        for(int i = 0; i < N; i++){
            int idx = i / 5;
            for(int j = 0; j < 3; j++){
                list[idx][j] += arr[j][map.get(minerals[i])];
            }
        }
        
        DFS(0, picks, 0, L);
        
        return answer;
    }
    
    private void DFS(int start, int[] picks, int sum, int L){
        if(start == L){
            answer = Math.min(answer, sum);
            return;
        }
        
        for(int j = 0; j < 3; j++){
            if(picks[j] == 0) continue;
            picks[j]--;
            DFS(start+1, picks, sum + list[start][j], L);
            picks[j]++;
        }
    }
}