import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        Node[] univ = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            univ[i] = new Node(cost, time);
        }

        Arrays.sort(univ, (o1, o2) -> {
            if(o2.cost == o1.cost) return o2.time - o1.time;
            return o2.cost - o1.cost;
        });

        boolean[] visited = new boolean[10001];

        int answer = 0;
        for (Node now : univ) {
            for (int i = now.time; i > 0; i--) {
                if(visited[i]) continue;
                visited[i] = true;
                answer+=now.cost;
                break;
            }
        }
        System.out.println(answer);
    }

    static class Node {
        int cost, time;

        public Node(int cost, int time) {
            this.cost = cost;
            this.time = time;
        }
    }
}