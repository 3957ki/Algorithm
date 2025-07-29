import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, Integer>[] dp = new Map[N+1];
        for(int i = 2; i <= N; i++){
            dp[i] = new HashMap<>();
        }
        dp[2].put(1, 1);

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < M; i++){
            int num = Integer.parseInt(br.readLine());
            set.add(num);
        }

        for(int i = 2; i < N; i++){
            if(set.contains(i)) continue;

            for(Integer now : dp[i].keySet()){
                for(int j = -1; j <= 1; j++){
                    int idx = i + now + j;
                    if(!set.contains(idx) && now + j > 0 && idx <= N){
                        if(!dp[idx].containsKey(now + j) || dp[idx].get(now + j) > dp[i].get(now) + 1){
                            dp[idx].put(now + j, dp[i].get(now) + 1);
                        }
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for(Integer num : dp[N].values()){
            answer = Math.min(answer, num);
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

}