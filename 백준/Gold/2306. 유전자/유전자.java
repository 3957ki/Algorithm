import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
		int N = arr.length;

		int[][] dp = new int[N][N];
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < N - i; j++) {
				int start = j;
				int end = j + i;

				// at나 gc인 경우 -> 내부 구간 최대 길이 + 2
				if((arr[start] == 'a' && arr[end] == 't') || (arr[start] == 'g' && arr[end] == 'c')) {
					dp[start][end] = dp[start+1][end-1] + 2;
				}

				// 구간 잘라서 이어붙이기
				for(int m = start; m < end; m++){
					dp[start][end] = Math.max(dp[start][end], dp[start][m] + dp[m+1][end]);
				}
			}
		}

		System.out.println(dp[0][N-1]);
    }
}