import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        parents = new int[N+1];
        Arrays.fill(parents, -1);

        int root = Integer.parseInt(st.nextToken());
        for (int i = 1; i < K; i++) {
            union(root, Integer.parseInt(st.nextToken()));
        }

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.add(new Edge(start, end, weight));
        }

        Collections.sort(edges, (o1, o2) -> o1.w-o2.w);

        int answer = 0;
        int cnt = 0;
        for (Edge edge : edges) {
            if(union(edge.s, edge.e)) {
                answer+=edge.w;
                if(++cnt == N-K) break;
            }
        }

        System.out.println(answer);
    }

    static int find(int a) {
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB) return false;
        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;
        return true;
    }

    static class Edge {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "s=" + s +
                    ", e=" + e +
                    ", w=" + w +
                    '}';
        }
    }

}