import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] dp = new int[K+1][T+1];
        dp[0][0] = 1;

        for(int i = 1; i <= K; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            for(int j = 0; j <= T; j++){
                if(dp[i-1][j] == 0) continue;

                dp[i][j] += dp[i-1][j];
                int L = Math.min(T, j+N*M);
                for(int next = j + N; next <= L; next+=N){
                    dp[i][next] += dp[i-1][j];
                }
            }
        }

        System.out.println(dp[K][T]);
    }
}