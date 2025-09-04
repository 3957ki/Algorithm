import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        
        int N = food_times.length;
        
        int cnt = N;
        
        int[] food_copy = new int[N];
        for(int i = 0; i < N; i++){
            food_copy[i] = food_times[i];
        }
        
        Arrays.sort(food_copy);
        
        long t = 0;
        for(int now : food_copy){
            if(now <= t){
                cnt--;
                continue;
            }
            
            long temp = (long)(now-t) * cnt;
            if(temp <= k){
                k -= temp;
                t += now - t;
                cnt--;
            }
            else break;
        }
        
        List<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < N; i++){
            if(food_times[i] <= t) continue;
            list.add(i + 1);
        }
        
        int L = list.size();
        
        return L == 0 ? -1 : list.get((int)(k % L));
    }
}