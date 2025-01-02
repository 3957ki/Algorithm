import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Node> list = new ArrayList<>();
    static int L, H, answer;
    static Node home = new Node();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if(num == 2) list.add(new Node(i, j));
                if(num == 1) {
                    home.y = i;
                    home.x = j;
                }
            }
        }

        L = list.size();

        func(home, M, 0, 0);

        System.out.println(answer);
    }

    static void func(Node now, int hp, int visited, int cnt) {

        // 집까지 갈 수 있다면
        if(Math.abs(home.y - now.y) + Math.abs(home.x - now.x) <= hp){
            answer = Math.max(answer, cnt);
        }

        for(int i = 0; i < L; i++) {
            if((visited&1<<i) != 0) continue;
            Node next = list.get(i);
            int dst = Math.abs(next.y - now.y) + Math.abs(next.x - now.x);
            // 거리가 멀면 못감
            if(dst > hp) continue;
            func(next, hp-dst+H, visited|1<<i, cnt+1);
        }
    }

    static class Node {
        int y, x;

        public Node() {
        }

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}