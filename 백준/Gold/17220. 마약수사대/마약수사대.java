import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] edges = new List[N];
        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }

        Set<Integer> candidates = new HashSet<>();

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            int start = str.charAt(0) - 'A';
            int end = str.charAt(2) - 'A';
            candidates.add(end);
            edges[start].add(end);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        int cnt = Integer.parseInt(st.nextToken());
        for (int i = 0; i < cnt; i++) {
            int now = st.nextToken().charAt(0) - 'A';
            visited[now] = true;
            edges[now] = new ArrayList<>();
        }

        for(int i = 0; i < N; i++){
            if(!candidates.contains(i)) {
                visited[i] = true;
                queue.add(i);
            }
        }

        int answer = 0;
        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : edges[now]) {
                if(!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                    answer++;
                }
            }
        }

        System.out.println(answer);
    }
}