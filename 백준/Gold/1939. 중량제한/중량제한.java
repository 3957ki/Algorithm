import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int[] parents;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(start, end, weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 가중치 내림차순
        Collections.sort(edges, (o1, o2) -> o2.weight - o1.weight);

        int left = 1;
        int right = 1000000000;
        int answer = 0;

        makeSet(N);
        // 이진 탐색을 통해 최대 가중치 탐색
        while (left <= right) {
            int mid = (left + right) / 2;

            // Union-Find 초기화
            Arrays.fill(parents, -1);

            // 현재 중량 이상으로 연결 가능한지 확인
            if (canConnect(start, end, edges, mid)) {
                answer = mid;
                left = mid + 1;  // 더 큰 가중치를 시도
            } else {
                right = mid - 1; // 더 작은 가중치를 시도
            }
        }

        System.out.println(answer);
    }

    static void makeSet(int N) {
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = -1;
        }
    }

    static int findSet(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = findSet(parents[a]);
    }

    static boolean union(int a, int b) {
        int rootA = findSet(a);
        int rootB = findSet(b);
        if (rootA == rootB) return false;
        parents[rootA] += parents[rootB];
        parents[rootB] = rootA;
        return true;
    }

    static boolean canConnect(int start, int end, List<Edge> edges, int weightLimit) {
        for (Edge edge : edges) {
            // 현재 중량 이상인 간선만 고려
            if (edge.weight >= weightLimit) {
                union(edge.start, edge.end);
                // 두 공장이 연결되었는지 확인
                if (findSet(start) == findSet(end)) {
                    return true;
                }
            }
        }
        return false;
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