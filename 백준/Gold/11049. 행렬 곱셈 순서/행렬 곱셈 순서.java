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
		
//		dp[묶음수][시작점]
		int[][] dp = new int[N+1][N+1];
		for(int i = 1; i < N; i++) {
			dp[2][i] = arr[i].first*arr[i].second*arr[i+1].second;
		}
		
//		묶음 수
		for(int i = 3; i <= N; i++) {
//			시작점
			for(int j = 1; j <= N-i+1; j++) {
				int min = Integer.MAX_VALUE;
//				묶음을 k 만큼 잘라서 계산해보기
				for(int k = 1; k < i; k++) {
//					시작점의 첫번째 수 x 끝점의 두번째 수 x k만큼 떨어진 값의 첫번째 수
					int temp = arr[j].first*arr[j+i-1].second*arr[j+k].first;
//					temp + 자른 첫번째 dp값 + 자른 두번째 dp값
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