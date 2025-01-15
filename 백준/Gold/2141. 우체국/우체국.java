import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        long total = 0;
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int pos = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());
            total += cnt;
            nodes[i] = new Node(pos, cnt);
        }

        Arrays.sort(nodes, (o1, o2) -> o1.pos - o2.pos);

        total = (total+1)/2;
        long cur = 0;
        for (Node now : nodes) {
            cur += now.cnt;
            if(cur >= total) {
                System.out.println(now.pos);
                break;
            }
        }
    }

    static class Node {
        int pos, cnt;

        public Node(int pos, int cnt) {
            this.pos = pos;
            this.cnt = cnt;
        }
    }
}