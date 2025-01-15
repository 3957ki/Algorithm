import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            List<Edge>[] edges = new List[M];
            for (int i = 0; i < M; i++) {
                edges[i] = new ArrayList<>();
            }

            while(N-- > 0) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                edges[start].add(new Edge(end, weight));
                edges[end].add(new Edge(start, weight));
            }

            PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);

            boolean[] visited = new boolean[M];
            int[] dst = new int[M];
            Arrays.fill(dst, INF);
            dst[0] = 0;
            pq.add(new Edge(0, 0));

            List<Integer> path = new ArrayList<>();

            while (!pq.isEmpty()) {
                Edge now = pq.poll();

                if(visited[now.v]) continue;
                visited[now.v] = true;

                if(now.v == M - 1) {
                    Edge temp = now;
                    while(temp.tail != null) {
                        path.add(temp.v);
                        temp = temp.tail;
                    }
                    break;
                }

                for (Edge next : edges[now.v]) {
                    if(visited[next.v]) continue;
                    if(dst[next.v] > dst[now.v] + next.w) {
                        dst[next.v] = dst[now.v] + next.w;
                        pq.add(new Edge(next.v, dst[next.v], now));
                    }
                }
            }

            // 도착 불가
            if(dst[M-1] == Integer.MAX_VALUE) sb.append("Case #").append(t).append(": -1").append('\n');
            else {
                sb.append("Case #").append(t).append(": 0 ");
                for (int i = path.size()-1; i >= 0; i--) {
                    sb.append(path.get(i)).append(' ');
                }
                sb.append('\n');
            }
        }
        System.out.println(sb);
    }

    static class Edge {
        int v, w;
        Edge tail;

        public Edge(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public Edge(int v, int w, Edge tail) {
            this.v = v;
            this.w = w;
            this.tail = tail;
        }

    }
}