import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Node[] arr = new Node[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = new Node(Integer.parseInt(st.nextToken()));
		}
		
		int L = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i].v = Integer.parseInt(st.nextToken());
			L+=arr[i].v;
		}
		
		int[] dp = new int[L+1];
		
		int answer = Integer.MAX_VALUE;
		
		for(int i = 0; i < N; i++) {
			for(int j = L; j >= arr[i].v; j--) {
				dp[j] = Math.max(dp[j], arr[i].w+dp[j-arr[i].v]);
				if(dp[j] >= M) {
					answer = Math.min(answer, j);
				}
			}
		}
		System.out.println(answer);
	}

	static class Node{
		int v, w;

		public Node(int w) {
			super();
			this.w = w;
		}
		
	}
}