import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[][] map;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        Node[] items = new Node[A+1];

        map = new int[N+1][M+1];
        dp = new int[N+1][M+1][2];

        for (int i = 0; i < A; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            items[i] = new Node(y, x);
        }
        items[A] = new Node(N, M);

        for (int i = 0; i < B; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            map[y][x] = -1;
        }

        // 아이템 정렬
        Arrays.sort(items, (o1, o2) -> {
            if(o1.y == o2.y) return o1.x - o2.x;
            return o1.y - o2.y;
        });

        Node start = new Node(1, 1);
        map[1][1] = 1;

        for (int k = 0; k <= A; k++) {
            Node next = items[k];

            for(int i = start.y; i <= next.y; i++) {
                for (int j = start.x; j <= next.x; j++) {
                    if(map[i][j] == -1) continue;
                    int y = i - 1;
                    int x = j - 1;
                    if (x >= start.x && map[i][x] != -1) {
                        map[i][j] += map[i][x];
                    }
                    if (y >= start.y && map[y][j] != -1) {
                        map[i][j] += map[y][j];
                    }
                }
            }
            start = next;
        }

        System.out.println(map[N][M]);

    }

    static class Node{
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}