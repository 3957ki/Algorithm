import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		Integer.parseInt(st.nextToken());
		Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken())^1;
		
		List<Edge>[] edges = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken())^1;
			
			edges[start].add(new Edge(end, weight));
			edges[end].add(new Edge(start, weight));
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		boolean[] visited = new boolean[N+1];
		pq.add(new Edge(1, w));
		visited[0] = true;
		
		int dst = 0;
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(visited[now.v]) continue;
			visited[now.v] = true;
			
			dst+=now.w;
			
			for(Edge next : edges[now.v]) {
				if(visited[next.v]) continue;
				pq.add(next);
			}
		}
		
		int good = dst*dst;
		
		pq = new PriorityQueue<>((o1, o2) -> o2.w - o1.w);
		visited = new boolean[N+1];
		pq.add(new Edge(1, w));
		visited[0] = true;
		
		dst = 0;
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(visited[now.v]) continue;
			visited[now.v] = true;
			
			dst+=now.w;
			
			for(Edge next : edges[now.v]) {
				if(visited[next.v]) continue;
				pq.add(next);
			}
		}
		int bad = dst*dst;
		System.out.println(bad-good);
	}

	static class Edge{
		int v, w;

		public Edge(int v, int w) {
			super();
			this.v = v;
			this.w = w;
		}
		
	}
}