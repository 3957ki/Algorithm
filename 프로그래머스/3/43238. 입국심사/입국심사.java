import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        
        int L = times.length;
        long low = 0;
        long high = 0;

        for(int time : times){
            high = Math.max(high, time);
        }
        
        high *= n;
        
        long mid = 0;
        long answer = 0;
        
        while(low <= high){
            mid = (low + high) >> 1;
            
            if(calc(mid, times, n)){
                answer = mid;
                high = mid - 1;
            } else{
                low = mid + 1;
            }
            
        }
        return answer;
    }
    
    static boolean calc(long mid, int[] times, int n){
        
        long sum = 0;
        
        for(int now : times){
            sum += mid / now;
            if(sum >= n) return true;
        }
        
        return false;
    }
}