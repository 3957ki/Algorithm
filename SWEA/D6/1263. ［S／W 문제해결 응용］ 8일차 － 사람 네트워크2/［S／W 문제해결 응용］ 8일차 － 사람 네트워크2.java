import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			
			int[][] edges = new int[N+1][N+1];
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					edges[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<Integer> queue = new ArrayDeque<>();
			int answer = Integer.MAX_VALUE;
			
			for(int i = 1; i <= N; i++) {
				boolean[] visited = new boolean[N+1];
				visited[i] = true;
				queue.add(i);
				int sum = 0;
				int dst = 0;
				while(!queue.isEmpty()) {
					dst++;
					int L = queue.size();
					while(L-- > 0) {
						int now = queue.poll();
						for(int j = 1; j <= N; j++) {
							if(visited[j] || edges[now][j] != 1) continue;
							visited[j] = true;
							queue.add(j);
							sum+=dst;
						}
					}
				}
				answer = Math.min(answer, sum);
			}
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

}