import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        
        int answer = 0;
        int prev = -1;
        for(int[] target : targets){
            if(target[0] >= prev){
                answer++;
                prev = target[1];
            }
        }
        
        return answer;
    }
}