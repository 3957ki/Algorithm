import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int farNode = 1, max;
    static int[] count;
    static List<Integer>[] edges;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        // [정점번호] = 열매 개수
        count = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            count[i] = Integer.parseInt(st.nextToken());
        }

        edges = new List[N+1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            edges[start].add(end);
            edges[end].add(start);
        }
        visited = new boolean[N+1];

        visited[1] = true;
        DFS(1, 0);
        visited[1] = false;

        max = 0;
        int start = farNode;
        visited[start] = true;
        DFS(farNode, count[farNode]);

        System.out.println(max +" "+ Math.min(farNode, start));
    }

    static void DFS(int now, int sum) {

        if(max < sum){
            max = sum;
            farNode = now;
        }
        else if(max == sum) farNode = Math.min(now, farNode);

        for (Integer next : edges[now]) {
            if(visited[next]) continue;
            visited[next] = true;
            DFS(next, sum+count[next]);
            visited[next] = false;
        }

    }
}