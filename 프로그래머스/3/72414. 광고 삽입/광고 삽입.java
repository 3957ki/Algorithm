import java.util.*;

class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        
        int total = Integer.valueOf(play_time.substring(0, 2)) * 3600 + Integer.valueOf(play_time.substring(3, 5)) * 60 + Integer.valueOf(play_time.substring(6, 8));
        
        int dst = Integer.valueOf(adv_time.substring(0, 2)) * 3600 + Integer.valueOf(adv_time.substring(3, 5)) * 60 + Integer.valueOf(adv_time.substring(6, 8));
        
        int N = logs.length;
        Map<Integer, Integer> map = new HashMap<>();
        
        for(int i = 0; i < N; i++){
            int start = Integer.valueOf(logs[i].substring(0, 2)) * 3600 + Integer.valueOf(logs[i].substring(3, 5)) * 60 + Integer.valueOf(logs[i].substring(6, 8));
            
            int end = Integer.valueOf(logs[i].substring(9, 11)) * 3600 + Integer.valueOf(logs[i].substring(12, 14)) * 60 + Integer.valueOf(logs[i].substring(15, 17));
            
            map.put(start+1, map.getOrDefault(start+1, 0) + 1);
            map.put(end+1, map.getOrDefault(end+1, 0) - 1);
        }
        
        Queue<int[]> queue = new ArrayDeque<>();

        long max = 0;
        long value = 0;
        int cnt = 0;
        int prev = 0;
        int answer = 0;
        int[] arr = new int[total+1];
        
        for(int i = 0; i <= dst; i++){
            cnt += map.get(i) == null ? 0 : map.get(i);
            value += cnt;
            arr[i] = cnt;
        }
        
        max = value;
        
        for(int i = dst+1; i <= total; i++){
            cnt += map.get(i) == null ? 0 : map.get(i);
            value += cnt - arr[++prev];
            arr[i] = cnt;

            if(max < value){
                max = value;
                answer = prev;
            }
        }
        System.out.println(total +" " +dst+" "+ answer+" "+max + " "+cnt);
        int h = answer / 3600;
        int m = (answer % 3600) / 60;
        int s = (answer % 3600) % 60;
        
        StringBuilder res = new StringBuilder();
        if(h / 10 == 0) res.append(0);
        res.append(h).append(':');
        if(m / 10 == 0) res.append(0);
        res.append(m).append(':');
        if(s / 10 == 0) res.append(0);
        res.append(s);
        
        return res.toString();
    }
    
}