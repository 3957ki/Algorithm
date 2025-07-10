import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks);
        
        int L = rocks.length;
        
        int high = distance;
        int low = 0;
        int mid;
        int answer = 0;
        
        A: while(low <= high){
            mid = (low + high) >> 1;
            
            int prev = 0;
            int cnt = 0;
            for(int i = 0; i < L; i++){
                if(rocks[i] - prev >= mid){
                    prev = rocks[i];
                }
                else if(++cnt > n) {
                    high = mid - 1;
                    continue A;
                }
            }
            
            if(distance - prev < mid && ++cnt > n){
                high = mid - 1;
            } else{
                low = mid + 1;
                answer = Math.max(answer, mid);
            }
        }
        
        return answer;
        
    }
    
}