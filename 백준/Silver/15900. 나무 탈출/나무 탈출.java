import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] edges;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        edges = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        for(int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            edges[start].add(end);
            edges[end].add(start);
        }

        for(int now : edges[1]) {
            DFS(now, 1, 1);
        }

        System.out.println((answer&1) == 0 ? "No" : "Yes");
    }

    static void DFS(int now, int cnt, int prev) {

        if(edges[now].size() == 1){
            answer += cnt;
            return;
        }

        for(int next : edges[now]){
            if(prev == next) continue;
            DFS(next, cnt + 1, now);
        }
    }
}