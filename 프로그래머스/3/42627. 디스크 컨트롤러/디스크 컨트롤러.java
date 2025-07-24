import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int N = jobs.length;
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> {
            if(jobs[o1][1] == jobs[o2][1]){
                if(jobs[o1][0] == jobs[o2][0]){
                    return o1 - o2;
                }
                return jobs[o1][0] - jobs[o2][0];
            }
            return jobs[o1][1] - jobs[o2][1];
        });
        
        int time = jobs[0][0];
        int idx = 0;
        int answer = 0;
        
        pq.add(0);
        for(int i = 1; i < N; i++){
            if(jobs[i][0] == time){
                pq.add(i);
                idx = i;
            }
            else break;
        }
        
        int cnt = 0;
        while(!pq.isEmpty()){
            int now = pq.poll();
            time += jobs[now][1];
            answer += time - jobs[now][0];
            
            for(int i = idx + 1; i < N; i++){
                if(jobs[i][0] <= time) {
                    pq.add(i);
                    idx = i;
                }
                else break;
            }
            
            if(++cnt < N && pq.isEmpty()){
                pq.add(++idx);
                time = jobs[idx][0];
                for(int i = idx + 1; i < N; i++){
                    if(jobs[i][0] <= time) {
                        pq.add(i);
                        idx = i;
                    }
                    else break;
                }
            }
            
        }
        
        return answer / N;
    }
    
    // static class Node{
    //     int num;
    //     int time;
    //     int size;
    //     Node(int num, int time, int size){
    //         this.num = num;
    //         this.time = time;
    //         this.size = size;
    //     }
    // }
}