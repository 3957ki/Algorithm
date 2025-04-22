import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] dp = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			dp[i] = dp[i - 1] + Integer.parseInt(st.nextToken());
		}

		int low = 0;
		int high = dp[N];
		int mid;
		int answer = 0;
		while (low <= high) {
			mid = (low + high) / 2;

			int cnt = 0;
			int prev = 0;
			boolean flag = false;
			for (int i = 1; i <= N; i++) {
				if (dp[i] - prev >= mid) {
					if (dp[i] - prev == mid) {
						flag = true;
					}
					cnt++;
					prev = dp[i];
				}
			}

			if (cnt < K) {
				high = mid - 1;
			} else if (cnt > K) {
				low = mid + 1;
			} else if (flag) {
				answer = Math.max(answer, mid);
				low = mid + 1;
			} else {
				low = mid + 1;
			}
		}

		System.out.println(answer);
	}
}