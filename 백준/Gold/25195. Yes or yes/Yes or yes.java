import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] edges = new List[N+1];
        for(int i = 1; i <= N; i++) edges[i] = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            edges[start].add(end);
        }

        int S = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < S; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        while(!queue.isEmpty()) {
            int now = queue.poll();

//          만났다면 패스
            if(set.contains(now)) continue;

//          다음 갈곳이 없다면
            if(edges[now].isEmpty()) {
                System.out.println("yes");
                return;
            }
            for(int next : edges[now]) {
                    queue.add(next);

            }
        }

        System.out.println("Yes");
    }
}