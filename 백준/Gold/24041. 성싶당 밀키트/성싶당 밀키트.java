import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 중요
        List<Node> list1 = new ArrayList<>();

        // 안중요
        List<Node> list2 = new ArrayList<>();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            int O = Integer.parseInt(st.nextToken());

            if(O == 0) list1.add(new Node(S, L));
            else list2.add(new Node(S, L));
        }

        long low = 1;
        long high = Integer.MAX_VALUE;
        long mid;
        long answer = 0;

        A: while(low <= high){
            mid = (low + high) >> 1;

            long sum = 0;
            for(Node l1 : list1){
                sum += l1.S * Math.max(1, mid - l1.L);
                if(sum > G){
                    high = mid - 1;
                    continue A;
                }
            }

            PriorityQueue<Long> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o2, o1));
            for(Node l2 : list2){
                long val = l2.S * Math.max(1, mid - l2.L);
                sum += val;
                pq.add(val);
            }

            int cnt = 0;
            while(!pq.isEmpty() && cnt++ < K){
                sum -= pq.poll();
            }


            if(sum > G) high = mid - 1;
            else {
                answer = Math.max(answer, mid);
                low = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static class Node{
        // 부패속도, 기한
        int S, L;
        
        Node(int S, int L){
            this.S = S;
            this.L = L;
        }
    }
}