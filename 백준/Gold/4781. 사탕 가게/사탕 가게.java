import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = (int)(Math.round(Double.parseDouble(st.nextToken()) * 100));

			// 종료
			if(N == 0 && M == 0) break;

			int[] dp = new int[M+1];

			for(int i = 0; i < N; i++){
				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int p = (int)(Math.round(Double.parseDouble(st.nextToken()) * 100));

				for(int j = p; j <= M; j++){
					dp[j] = Math.max(dp[j], dp[j-p] + c);
				}
			}
			sb.append(dp[M]).append('\n');
		}
		System.out.println(sb);
    }
}