import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Integer>[] edges;
    static double total = 0;
    static int C = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        double W = Double.parseDouble(st.nextToken());

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

        DFS(1, 0, W);

        System.out.println(total/C);
    }

    static void DFS(int now, int prev, double sum) {

        // 받은게 없으면 return
        if(sum == 0) return;

        // 자식 수
        int cnt = edges[now].size();

        // 1이면 자식이 없음
        if(cnt == 1 && now != 1) {
            total += sum;
            C++;
            return;
        }

        // 1이 아니면 자식수 -1
        if(now != 1) cnt--;
        double w = sum/cnt;

        for (Integer next : edges[now]) {
            if (next == prev) continue;
            DFS(next, now, w);
        }
    }
}