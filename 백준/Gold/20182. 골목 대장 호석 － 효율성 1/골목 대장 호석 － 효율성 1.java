import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		List<Edge>[] edges = new List[N+1];
		for(int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges[A].add(new Edge(B, weight, 0));
			edges[B].add(new Edge(A, weight, 0));
			
		}
		
//		다익스트라
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		dist[start] = 0;
		pq.add(new Edge(start, 0, 0));
		
		int answer = Integer.MAX_VALUE;
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			int now = e.v;
			
			if(visited[now]) continue;
			visited[now] = true;
			
			for(Edge edge : edges[now]) {
				int next = edge.v;
				if(dist[next] > dist[now] + edge.w) {
//					가진 돈보다 많이들면 패스
					if(dist[now]+edge.w > C) continue;
					dist[next] = dist[now] + edge.w;
//					종료지점이라면 answer 갱신
					if(next == end) {
						answer = Math.min(answer, Math.max(e.c, edge.w));
					}
					pq.add(new Edge(next, dist[next], Math.max(e.c, edge.w)));
				}
			}
		}
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
		
	}
	
	static class Edge{
		int v, w, c;

		public Edge(int v, int w, int c) {
			this.v = v;
			this.w = w;
			this.c = c;
		}
		
	}

}