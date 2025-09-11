import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int N = gems.length;
        
        Map<String, Integer> map = new HashMap<>();
        
        for(String now : gems){
            if(!map.containsKey(now)){
                map.put(now, 0);
            }
        }
        
        int cnt = map.size();
        int prev = 0;
        int min = N;
        int[] answer = new int[2];
        
        for(int i = 0; i < N; i++){
            String now = gems[i];
            int c = map.get(now);
            
            if(map.get(now) == 0) cnt--;
            map.put(now, c + 1);
            
            while(cnt == 0){
                if(min > i - prev){
                    min = i - prev;
                    answer[0] = prev + 1;
                    answer[1] = i + 1;
                }
                
                int p = map.get(gems[prev]);
                map.put(gems[prev], p - 1);
                if(p == 1) cnt++;
                prev++;
            }
        }
        
        return answer;
    }
}