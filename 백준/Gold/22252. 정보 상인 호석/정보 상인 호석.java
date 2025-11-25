import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int Q = Integer.parseInt(br.readLine());

        Map<String, PriorityQueue<Integer>> map = new HashMap<>();

        long answer = 0;

        while(Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            String name = st.nextToken();

            if(cmd == 1) {
                if(!map.containsKey(name)) {
                    map.put(name, new PriorityQueue<>((o1, o2) -> o2 - o1));
                }

                PriorityQueue<Integer> pq = map.get(name);
                int K = Integer.parseInt(st.nextToken());
                for(int i = 0; i < K; i++) {
                    pq.add(Integer.parseInt(st.nextToken()));
                }
            }

            else{
                int cnt = Integer.parseInt(st.nextToken());
                PriorityQueue<Integer> pq = map.get(name);
                if(pq == null) continue;

                while(!pq.isEmpty()) {
                    answer += pq.poll();
                    if(--cnt == 0) break;
                }
            }
        }

        System.out.println(answer);
    }

}