import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[][] edges;
    static boolean[] visited;
    static Map<Integer, Integer> parents = new HashMap<>();
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 인접 행렬
        edges = new boolean[N+1][N+1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            edges[start][end] = true;
            edges[end][start] = true;
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        visited = new boolean[N+1];

        int answer = 0;
        answer += BFS(S, E);

        visited = new boolean[N+1];

        int son = E;
        while(true) {
            int parent = parents.get(son);
            if(parent == S) break;
            visited[parent] = true;
            son = parent;
        }
        answer += BFS(E, S);

        System.out.println(answer);

    }

    static int BFS(int start, int end) {

        Queue<Integer> queue = new ArrayDeque<>();
        visited[start] = true;
        queue.add(start);

        int dst = 0;
        A: while (!queue.isEmpty()) {
            dst++;
            int L = queue.size();

            while (L-- > 0) {
                int now = queue.poll();

                for (int next = 1; next <= N; next++) {
                    if(!edges[now][next]) continue;

                    if (visited[next]) continue;
                    visited[next] = true;

                    queue.add(next);
                    parents.put(next, now);
                    if(next == end) break A;
                }
            }
        }

        return dst;
    }

}