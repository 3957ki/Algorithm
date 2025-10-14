import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] edges = new int[N+1][N+1];

			while(M-- > 0) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());

				edges[start][end] = cost;
				edges[end][start] = cost;
			}

			for(int k = 1; k <= N; k++) {
				for(int i = 1; i <= N; i++) {
					for(int j = 1; j <= N; j++) {
						if(i == j) continue;
						if(edges[i][k] == 0 || edges[k][j] == 0) continue;
						edges[i][j] = edges[i][j] == 0 ? edges[i][k] + edges[k][j] : Math.min(edges[i][j], edges[i][k] + edges[k][j]);
					}
				}
			}

			int K = Integer.parseInt(br.readLine());
			int[] friends = new int[K];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < K; i++) {
				friends[i] = Integer.parseInt(st.nextToken());
			}

			int answer = 0;
			int dist = Integer.MAX_VALUE;

			for(int i = 1; i <= N; i++) {
				int sum = 0;
				for(int now : friends) {
					sum += edges[i][now];
				}
				if(sum < dist) {
					dist = sum;
					answer = i;
				}
			}
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
    }
}