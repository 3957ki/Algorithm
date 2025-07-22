import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        Map<Character, Queue<Integer>> map = new HashMap<>();

        int N = S.length();
        int M = T.length();

        for(int i = 0; i < M; i++){
            map.put(T.charAt(i), new ArrayDeque<>());
        }

        for(int i = 0; i < N; i++){
            char now = S.charAt(i);
            if(!map.containsKey(now)) continue;
            map.get(now).add(i);
        }

        int cnt = 0;
        A: while(true){
            int prev = 0;

            for(int i = 0; i < M; i++){
                Queue<Integer> now = map.get(T.charAt(i));
                while(!now.isEmpty() && now.peek() < prev){
                    now.poll();
                }
                if(now.isEmpty()) break A;
                prev = now.poll();
            }
            cnt++;
        }

        System.out.println(cnt);
    }
}