import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static Set<Integer>[] edges;
    static int N;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        answer = Integer.MAX_VALUE;

        edges = new Set[N+1];
        for (int i = 0; i <= N; i++) {
            edges[i] = new HashSet<>();
            edges[0].add(i);
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            edges[start].add(end);
            edges[end].add(start);
        }

        comb(1, 0, 0, 0, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void comb(int now, int cnt, int prev1, int prev2, int sum) {

        if(cnt == 3){
            answer = Math.min(answer, sum);
            return;
        }

        for(int i = now; i <= N; i++){
            if(!edges[prev1].contains(i) || !edges[prev2].contains(i)) continue;
            comb(i+1, cnt+1, i, prev1, sum + edges[i].size() - 2);
        }
    }
}