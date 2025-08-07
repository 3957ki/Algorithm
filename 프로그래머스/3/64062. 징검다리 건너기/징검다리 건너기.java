import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        
        int L = stones.length;
        
        int low = 1;
        int high = 200000000;
        int mid;
        int answer = 0;
        
        while(low <= high){
            mid = (low + high) >> 1;
            
            if(solve(stones, L, mid, k)){
                answer = Math.max(answer, mid);
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        
        return answer;
    }
    
    static boolean solve(int[] stones, int L, int mid, int k){
            
        int cnt = 0;
        for(int i = 0; i < L; i++){
            if(stones[i] - mid < 0) {
                if(++cnt >= k) return false;
            }
            else cnt = 0;
        }
        
        return true;
    }
}