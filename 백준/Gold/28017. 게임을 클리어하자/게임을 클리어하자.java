import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N+1][M+1];
        List<Node>[] dp = new List[N+1];

        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            dp[i] = new ArrayList<>();

            for(int j = 1; j <= M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i <= M; i++){
            dp[1].add(new Node(arr[1][i], i));
        }

        dp[1].sort((o1, o2) -> o1.sum - o2.sum);

        for(int i = 2; i <= N; i++){
            Node min1 = dp[i-1].get(0);
            Node min2 = dp[i-1].get(1);

            for(int j = 1; j <= M; j++){
                if(j != min1.idx){
                    dp[i].add(new Node(arr[i][j] + min1.sum, j));
                }
                else{
                    dp[i].add(new Node(arr[i][j] + min2.sum, j));
                }
            }
            dp[i].sort((o1, o2) -> o1.sum - o2.sum);
        }

        System.out.println(dp[N].get(0).sum);

    }

    static class Node {
        int sum;
        int idx;
        public Node(int sum, int idx) {
            this.sum = sum;
            this.idx = idx;
        }
    }
}