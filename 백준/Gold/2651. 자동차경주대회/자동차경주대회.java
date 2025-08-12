import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());

		int[] dst = new int[N+2];	// 거리 누적합
		int[] time = new int[N+2];	// 소요시간

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N + 1; i++) {
			dst[i] = dst[i-1] + Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			time[i] = Integer.parseInt(st.nextToken());
		}

		Node[] dp = new Node[N+2];
		for(int i = 0; i <= N + 1; i++) {
			dp[i] = new Node(Long.MAX_VALUE, 0, 0);
		}
		dp[0].sum = 0;
		dp[0].cnt = -1;

		for(int i = 0; i <= N; i++) {
			Node now = dp[i];

			if(now.sum == Long.MAX_VALUE) continue;

			for(int j = i + 1; j <= N + 1; j++) {
				int d = dst[j] - dst[i];
				if(d > L) break;
				if(now.sum + time[j] >= dp[j].sum) continue;
				dp[j].sum = now.sum + time[j];
				dp[j].cnt = now.cnt + 1;
				dp[j].prev = i;
			}
		}

		StringBuilder sb = new StringBuilder();
		sb.append(dp[N+1].sum).append('\n').append(dp[N+1].cnt).append('\n');
		DFS(sb, dp, N+1, N+1);

		System.out.println(sb);
    }

	static void DFS(StringBuilder sb, Node[] dp, int now, int L) {
		if(now == 0) return;
		DFS(sb, dp, dp[now].prev, L);
		if(now != L){
			sb.append(now).append(' ');
		}
	}

	static class Node {
		long sum;
		int cnt, prev;
		public Node(long sum, int cnt, int prev) {
			this.sum = sum;
			this.cnt = cnt;
			this.prev = prev;
		}
	}
}