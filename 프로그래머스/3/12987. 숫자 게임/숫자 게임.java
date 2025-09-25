import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int N = A.length;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        int answer = 0;
        int idx = 0;
        for(int now : A){
            while(idx < N){
                if(now < B[idx++]){
                    answer++;
                    break;
                }
            }
        }
        
        return answer;
    }
}