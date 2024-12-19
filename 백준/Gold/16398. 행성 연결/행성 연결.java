import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        long[][] edges = new long[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j ++){
                edges[i][j] = Long.parseLong(st.nextToken());
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.w));
        pq.add(new Edge(0,0));
        boolean[] visited = new boolean[N];

        long answer = 0;
        int cnt = 0;
        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if(visited[now.v]) continue;
            visited[now.v] = true;

            answer += now.w;
            if(++cnt == N) break;
            
            for(int next = 0; next < N; next++){
                if(visited[next]) continue;
                pq.add(new Edge(next, edges[now.v][next]));
            }
        }
        System.out.println(answer);
    }

    static class Edge{
        int v;
        long w;

        public Edge(int v, long w) {
            this.v = v;
            this.w = w;
        }
    }
}