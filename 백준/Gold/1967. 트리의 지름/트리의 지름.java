import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int max;
	static List<Edge>[] edges;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		edges = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for(int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[start].add(new Edge(end, weight));
			edges[end].add(new Edge(start, weight));
		}
		
		int answer = 0;
		for(int i = 1; i <= N; i++) {
			int first = 0;
			int second = 0;
			for(Edge edge : edges[i]) {
				max = edge.w;
				DFS(edge.v, edge.w, i);
				if(max > first) {
					second = first;
					first = max;
				}
				else if(max == first) {
					second = max;
				}
				else if(max > second) {
					second = max;
				}
			}
			answer = Math.max(first+second, answer);
		}
		System.out.println(answer);
	}
	
	static void DFS(int v, int sum, int prev) {
		max = Math.max(max, sum);
		
		for(Edge edge : edges[v]) {
			if(edge.v == prev) continue;
			DFS(edge.v, sum+edge.w, v);
		}
	}

	static class Edge{
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
	}
}