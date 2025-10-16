import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int L = cores.length;
        int low = 0;
        int high = 500000000;
        int time = 0;
        int cur = 0;
        
        A: while(low <= high){
            int mid = (low + high) >> 1;
            
            int sum = 0;
            for(int cnt : cores){
                sum += (mid + cnt - 1) / cnt;
                if(sum >= n){
                    high = mid - 1;
                    continue A;
                }
            }
            cur = sum;
            time = mid;
            low = mid + 1;
        }
        
        while(cur < n){
            for (int i = 0; i < L; i++) {
                if (time % cores[i] == 0 && ++cur == n) return i + 1;
            }
            time++;
        }
        
        return 0;
    }
    
}