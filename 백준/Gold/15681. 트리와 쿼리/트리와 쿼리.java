import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] size;
	static List<Integer>[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		
		size = new int[N+1];
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			list[start].add(end);
			list[end].add(start);
		}
		
		DFS(R, 0);
		
		for(int i = 0; i < Q; i++) {
			int now = Integer.parseInt(br.readLine());
			sb.append(size[now]).append('\n');
		}
		System.out.println(sb);
	}

	static void DFS(int cur, int prev) {
		
		size[cur] = 1;
		for(Integer child : list[cur]) {
			if(child == prev) continue;
			DFS(child, cur);
			size[cur] += size[child];
		}
	}
}