import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[] gender = new char[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            gender[i] = st.nextToken().charAt(0);
        }

        parents = new int[N+1];
        Arrays.fill(parents, -1);

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            if(gender[start] == gender[end]) continue;
            edges.add(new Edge(start, end, weight));
        }

        edges.sort((o1, o2) -> o1.weight - o2.weight);

        int cnt = 0;
        int dst = 0;
        for (Edge now : edges) {

            if(union(now.start, now.end)) {
                dst += now.weight;
                if(++cnt == N-1) break;
            }
        }

        System.out.println(cnt == N-1 ? dst : -1);
    }

    static int find(int a){
        if(parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parents[aRoot] += parents[bRoot];
        parents[bRoot] = aRoot;
        return true;
    }

    static class Edge {
        int start, end, weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
}