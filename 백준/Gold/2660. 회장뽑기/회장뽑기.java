import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int[][] edges = new int[N+1][N+1];
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			
			if(start == -1 && end == -1) break;
			
			edges[start][end] = 1;
			edges[end][start] = 1;
		}
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(i == j) continue;
					if(edges[i][k] == 0 || edges[k][j] == 0) continue;
					if(edges[i][j] == 0) {
						edges[i][j] = edges[i][k]+edges[k][j];
						edges[j][i] = edges[i][j];
						continue;
					}
					edges[i][j] = Math.min(edges[i][j], edges[i][k]+edges[k][j]);
					edges[j][i] = edges[i][j];
				}
			}
		}
		
//		후보 수
		List<Integer> list = new ArrayList<>();
		int min = Integer.MAX_VALUE;
		for(int i = 1; i <= N; i++) {
			int max = 0;
			for(int j = 1; j <= N; j++) {
				max = Math.max(max, edges[i][j]);
			}
			if(min > max) {
				list = new ArrayList<>();
				list.add(i);
				min = max;
			}
			else if(min == max) list.add(i);
		}
		
		sb.append(min).append(' ').append(list.size()).append('\n');
		for(int i : list) {
			sb.append(i).append(' ');
		}
		System.out.println(sb);
	}

}