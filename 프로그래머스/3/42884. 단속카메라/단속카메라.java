import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        
        // 차 개수
        int N = routes.length;
        
        // 정렬
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        
        int answer = 0;
        
        int prev = -30001;
        for(int i = 0; i < N; i++){
            if(prev < routes[i][0]) {
                answer++;
                prev = routes[i][1];
            }
        }
        return answer;
    }
}