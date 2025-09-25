import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int N = timetable.length;
        int[] arr = new int[N];
        
        for(int i = 0; i < N; i++){
            int hour = Integer.parseInt(timetable[i].substring(0, 2));
            int min = Integer.parseInt(timetable[i].substring(3, 5));
            
            arr[i] = hour*60 + min;
        }
        
        Arrays.sort(arr);
        
        int prev = 0;
        int last = -1;
        int cnt = 0;
        int now = 540;
        for(int i = 0; i < n; i++){
            cnt = 0;
            
            while(prev < N){
                if(arr[prev] > now) break;
                last = arr[prev];
                prev++;
                if(++cnt == m) break;
            }
            now += t;
        }
        
        now -= t;
        
        int hour, minute;
        if(cnt < m) {
            hour = (now/60);
            minute = (now%60);
        }
        else{
            last--;
            hour = (last/60);
            minute = (last%60);
        }
        
        String answer = "";
        
        if(hour / 10 == 0) answer += 0;
        answer += hour + ":";
        
        if(minute / 10 == 0) answer += 0;
        answer += minute;
        
        return answer;
    }
}