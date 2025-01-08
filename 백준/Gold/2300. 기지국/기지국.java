import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st;

        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes[i] = new Node(y, x);
        }

        // x좌표 오름차순
        Arrays.sort(nodes, Comparator.comparingInt(o -> o.x));

        int[] dp = new int[N+1];

        for (int i = 1; i <= N; i++) {
            dp[i] = Integer.MAX_VALUE;
            int maxY = 0;
            for (int j = i; j >= 1; j--) {
                maxY = Math.max(maxY, Math.abs(nodes[j-1].y));
                dp[i] = Math.min(dp[i], dp[j-1] + Math.max(nodes[i-1].x - nodes[j-1].x, 2*maxY));
            }
        }

        System.out.println(dp[N]);
    }

    static class Node {
        int y, x;

        public Node(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}