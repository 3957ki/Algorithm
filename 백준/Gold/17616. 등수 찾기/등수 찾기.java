import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static boolean[] visited;
    static int high, low;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        Node[] nodes = new Node[N+1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new Node(i);
        }

        visited = new boolean[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nodes[a].tail.add(nodes[b]);
            nodes[b].head.add(nodes[a]);
        }

        tail(nodes[X]);
        head(nodes[X]);

        System.out.println((high+1) + " " + (N-low));
    }

    static void tail(Node node) {

        for(Node now : node.tail) {
            if(visited[now.num]) continue;
            visited[now.num] = true;
            low++;
            tail(now);
        }
    }

    static void head(Node node) {

        for (Node now : node.head) {
            if(visited[now.num]) continue;
            visited[now.num] = true;
            high++;
            head(now);
        }
    }

    static class Node {
        List<Node> head = new ArrayList<>();
        List<Node> tail = new ArrayList<>();
        int num;

        public Node(int num) {
            this.num = num;
        }
    }
}