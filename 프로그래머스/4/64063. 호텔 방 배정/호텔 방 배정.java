import java.util.*;

class Solution {
    public long[] solution(long k, long[] room_number) {
        
        Map<Long, Long> parents = new HashMap<>();
        int L = room_number.length;
        long[] answer = new long[L];
        
        for(int i = 0; i < L; i++){
            long now = room_number[i];
            
            if(!parents.containsKey(now)){
                answer[i] = now;
                parents.put(now, now+1);
            } else{
                answer[i] = find(now, parents);
                parents.put(answer[i], answer[i]+1);
            }
        }
        
        return answer;
    }
    
    static long find(long a, Map<Long, Long> parents){
        if(!parents.containsKey(a)) return a;
        long next = find(parents.get(a), parents);
        parents.put(a, next);
        return next;
    }
    
    
}