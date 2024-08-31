import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static List<Integer>[] list;
	static int[][] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		list = new ArrayList[N+1];
		dp = new int[N+1][2];
		
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list[start].add(end);
			list[end].add(start);
		}
		
		DFS(1, 0);
		
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	static void DFS(int cur, int prev) {
//		선택할때 1, 안할때 0
		dp[cur][0] = 0;
		dp[cur][1] = 1;
		
		for(Integer child : list[cur]) {
			if(child == prev) continue;
			DFS(child, cur);
//			현재 선택 안하면, 자식은 무조건 early
			dp[cur][0] += dp[child][1];
//			현재 선택 하면, 자식이 early일때와 아닐때 중 최소값으로 갱신
			dp[cur][1] += Math.min(dp[child][0], dp[child][1]);
		}
	}

}