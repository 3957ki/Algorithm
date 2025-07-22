import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[][] dp = new boolean[N+1][15001];
        dp[0][0] = true;

        for(int i = 1; i <= N; i++){
            int now = Integer.parseInt(st.nextToken());
            for(int j = 0; j <= 15000 - now; j++){
                // 이미 만들 수 있는 무게
                if(dp[i - 1][j]){
                    dp[i][j] = true;
                    dp[i][j + now] = true;
                    dp[i][Math.abs(j - now)] = true;
                }
            }
        }


        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < M; i++){
            // 구슬
            int now = Integer.parseInt(st.nextToken());

            if(now <= 15000 && dp[N][now]) sb.append('Y');
            else sb.append('N');
            sb.append(' ');
        }

        System.out.println(sb);
    }
}