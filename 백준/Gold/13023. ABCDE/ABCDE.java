import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static List<Integer>[] edge;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		edge = new ArrayList[N];
		for(int i = 0; i < N; i++) {
			edge[i] = new ArrayList<>();
		}
		boolean[] visited = new boolean[N];

		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			edge[start].add(end);
			edge[end].add(start);
		}

		for(int i = 0; i < N; i++) {
			visited[i] = true;
			DFS(i, 1, visited);
			visited[i] = false;
		}
		System.out.println(0);
	}

	static void DFS(int n, int cnt, boolean[] visited) {
		if(cnt == 5) {
			System.out.println(1);
			System.exit(0);
		}

		for(int num : edge[n]) {
			if(visited[num]) continue;
			visited[num] = true;
			DFS(num, cnt+1, visited);
			visited[num] = false;
		}
	}
}