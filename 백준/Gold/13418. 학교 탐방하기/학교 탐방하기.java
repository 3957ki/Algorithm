import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] parents;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		Arrays.fill(parents, -1);
		
		st = new StringTokenizer(br.readLine());
		Integer.parseInt(st.nextToken());
		Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken())^1;
		
		List<Edge> edges = new ArrayList<>();
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken())^1;
			
			edges.add(new Edge(end, start, weight));
			edges.add(new Edge(start, end, weight));
		}
		
		Collections.sort(edges, (o1, o2) -> o1.w - o2.w);
		
		int good = w;
		int cnt = 0;
		for(Edge edge : edges) {
			if(union(edge.start, edge.end)) {
				good+=edge.w;
				if(++cnt == N-1) break;
			}
		}
		
		good *= good;
		
		Collections.sort(edges, (o1, o2) -> o2.w - o1.w);
		
		parents = new int[N+1];
		Arrays.fill(parents, -1);
		
		int bad = w;
		cnt = 0;
		for(Edge edge : edges) {
			if(union(edge.start, edge.end)) {
				bad+=edge.w;
				if(++cnt == N-1) break;
			}
		}
		
		bad *= bad;
		
		System.out.println(bad-good);
	}
	
	static int find(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return false;
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}

	static class Edge{
		int start, end , w;

		public Edge(int start, int end, int w) {
			super();
			this.start = start;
			this.end = end;
			this.w = w;
		}

	}
}