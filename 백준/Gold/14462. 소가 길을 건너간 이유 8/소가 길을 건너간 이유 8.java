import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] left = new int[N+1];
        int[] right = new int[N+1];

        for (int i = 1; i <= N; i++) {
            left[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            right[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[N+1];
        int[] temp = new int[N+1];
        for(int i = 1; i <= N; i++) {
            int max = 0;

            for(int j = 1; j <= N; j++) {
                max = Math.max(max, dp[j-1]);
                if(Math.abs(left[i] - right[j]) > 4) continue;
                temp[j] = Math.max(dp[j], max + 1);
            }

            for(int j = 1; j <= N; j++) {
                dp[j] = Math.max(dp[j], temp[j]);
            }
        }

        int answer = 0;
        for(int cnt : dp){
            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
}