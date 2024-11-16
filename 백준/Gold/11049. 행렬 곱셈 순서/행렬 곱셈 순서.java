import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		if(N == 1) {
			System.out.println(0);
			return;
		}
		Node[] arr = new Node[N+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			arr[i] = new Node(first, second);
		}
		
		int[][] dp = new int[N+1][N+1];
		for(int i = 1; i < N; i++) {
			dp[2][i] = arr[i].first*arr[i].second*arr[i+1].second;
		}
		
		for(int i = 3; i <= N; i++) {
			for(int j = 1; j <= N-i+1; j++) {
				int min = Integer.MAX_VALUE;
				for(int k = 1; k < i; k++) {
					int temp = arr[j].first*arr[j+i-1].second*arr[j+k].first;
					min = Math.min(temp+dp[k][j]+dp[i-k][j+k], min);
				}
				dp[i][j] = min;
			}
		}
		
		System.out.println(dp[N][1]);
		
	}

	static class Node{
		int first, second;

		public Node(int first, int second) {
			super();
			this.first = first;
			this.second = second;
		}
		
	}
}