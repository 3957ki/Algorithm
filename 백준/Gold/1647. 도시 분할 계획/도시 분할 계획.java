import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Edge[] edges = new Edge[M];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(edges, (o1, o2) -> o1.weight - o2.weight);
		parents = new int[N+1];
		make();
		
		int cnt = 0;
		int cost = 0;
		for(Edge edge : edges) {
			if(union(edge.start, edge.end)) {
				if(++cnt == N-1) break;
				cost += edge.weight;
			}
			
		}
		System.out.println(cost);
	}
	
	static void make() {
		for(int i = 1; i <= N; i++) {
			parents[i] = -1;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
//		집합의 크기 관리
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}
	
	static class Edge{
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
	}
	
}