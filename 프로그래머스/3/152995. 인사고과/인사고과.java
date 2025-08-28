import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        
        int a = scores[0][0];
        int b = scores[0][1];
        
        // 점수 합 내림차순
        Arrays.sort(scores, (o1, o2) -> (o2[0]+o2[1]) - (o1[0]+o1[1]));
        
        Map<Integer, Integer> map = new TreeMap<>((o1, o2) -> o2 - o1);
        
        int N = scores.length;
        int prev = 200001;
        int answer = 0;
        int cnt = 0;
        
        int[] arr = new int[N];
        
        A: for(int i = 0; i < N; i++){
            int[] now = scores[i];
            map.put(now[0], Math.max(map.getOrDefault(now[0], 0), now[1]));
            
            for(int num : map.keySet()){
                if(num <= now[0]) break;
                if(map.get(num) > now[1]) {
                    cnt++;
                    arr[i] = -1;
                    if(now[0] == a && now[1] == b) return arr[i];
                    continue A;
                }
            }
            
            if(prev > now[0]+now[1]){
                answer = i-cnt+1;
                prev = now[0]+now[1];
            }
            arr[i] = answer;
            if(now[0] == a && now[1] == b) return arr[i];
        }
        
        return answer;
    }
}