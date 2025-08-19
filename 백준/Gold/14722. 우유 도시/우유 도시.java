import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

		int[][] map = new int[N][N];

		// 0: 딸기, 1: 초코, 2: 바나나
		int[][][] dp = new int[N][N][3];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) dp[i][j][0] = 1;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i > 0){
					for(int d = 0; d < 3; d++){
						dp[i][j][d] = Math.max(dp[i][j][d], dp[i-1][j][d]);
						if(map[i][j] == d && dp[i-1][j][(d+2)%3] > 0) dp[i][j][d] = Math.max(dp[i][j][d], dp[i-1][j][(d+2)%3] + 1);
					}
				}
				if(j > 0 && dp[i][j-1][0] > 0){
					for(int d = 0; d < 3; d++){
						dp[i][j][d] = Math.max(dp[i][j][d], dp[i][j-1][d]);
						if(map[i][j] == d && dp[i][j-1][(d+2)%3] > 0) dp[i][j][d] = Math.max(dp[i][j][d], dp[i][j-1][(d+2)%3] + 1);
					}
				}
			}
		}

		int answer = Math.max(Math.max(dp[N-1][N-1][0], dp[N-1][N-1][1]), dp[N-1][N-1][2]);
		System.out.println(answer);
    }

}