import java.util.*;

class Solution {
    public int solution(int n, int[] cores) {
        int L = cores.length;
        int low = 0;
        int high = 500000000;
        int time = 0;   // 현재 시간
        int cur = 0;    // 들어간 작업 수
        
        A: while(low <= high){
            int mid = (low + high) >> 1;
            
            // mid 시간에 투입 가능한 총 작업 수
            int sum = 0;
            for(int cnt : cores){
                sum += mid / cnt + 1;
                if(sum >= n){
                    high = mid - 1;
                    continue A;
                }
            }
            cur = sum;
            time = mid + 1;
            low = mid + 1;
        }
        
        while(cur < n){
            for (int i = 0; i < L; i++) {
                // 나머지가 0이면 작업 투입 가능
                if (time % cores[i] == 0 && ++cur == n) return i + 1;
            }
            time++;
        }
        
        return 0;
    }
    
}