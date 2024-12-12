import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
//		집 번호
		int[] home = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			home[i] = Integer.parseInt(st.nextToken());
		}
		
//		간선
		List<Edge>[] edges = new List[V+1];
		for(int i = 1; i <= V; i++) {
			edges[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[start].add(new Edge(end, weight));
			edges[end].add(new Edge(start, weight));
		}
		
		int answer = 0;
		
//		다익스트라
		for(int i = 0; i < N; i++) {
			PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
			int[] dst = new int[V+1];
			boolean[] visited = new boolean[V+1];
			Arrays.fill(dst, Integer.MAX_VALUE);
			dst[home[i]] = 0;
			pq.add(new Edge(home[i], 0));
			
			while(!pq.isEmpty()) {
				Edge now = pq.poll();
				
				if(visited[now.v]) continue;
				visited[now.v] = true;
				
				for(Edge next : edges[now.v]) {
					if(dst[next.v] > dst[now.v] + next.w) {
						dst[next.v] = dst[now.v] + next.w;
						pq.add(new Edge(next.v, dst[next.v]));
					}
				}
			}
			
			answer += dst[A] == Integer.MAX_VALUE ? -1 : dst[A];
			answer += dst[B] == Integer.MAX_VALUE ? -1 : dst[B];
		}
		
		System.out.println(answer);
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