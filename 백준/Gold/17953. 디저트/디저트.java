import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[][] arr = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[M][N];

		for (int i = 0; i < M; i++) {
			dp[i][0] = arr[i][0];
		}

		for (int j = 1; j < N; j++) {
			for(int i = 0; i < M; i++) {
				for(int k = 0; k < M; k++) {
					dp[i][j] = Math.max(dp[i][j], i == k ? dp[k][j-1] + arr[i][j] / 2 : dp[k][j-1] + arr[i][j]);
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < M; i++) {
			answer = Math.max(answer, dp[i][N-1]);
		}

		System.out.println(answer);
    }
}