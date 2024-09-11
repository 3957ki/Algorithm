import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

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
		
//		간선이 큰 순으로 저장
		TreeSet<Integer> set = new TreeSet<>((o1, o2) -> o2 - o1);
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges[A].add(new Edge(B, weight));
			edges[B].add(new Edge(A, weight));
			set.add(weight);
		}
		
		int answer = -1;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		boolean[] visited = new boolean[N+1];
		int[] dist = new int[N+1];
		for(Integer maxCost : set) {
//			다익스트라
			pq.clear();
			Arrays.fill(visited, false);
			Arrays.fill(dist, INF);
			dist[start] = 0;
			pq.add(new Edge(start, 0));
			
			while(!pq.isEmpty()) {
				int now = pq.poll().v;
//				도착지라면 종료
				if(now == end) break;
				if(visited[now]) continue;
				visited[now] = true;
				
				for(Edge edge : edges[now]) {
					int next = edge.v;
					if(dist[next] > dist[now] + edge.w) {
//						maxCost보다 큰 간선은 패스
						if(edge.w > maxCost) continue;
						dist[next] = dist[now] + edge.w;
						pq.add(new Edge(next, dist[next]));
					}
				}
			}
//			가진 돈보다 많이 필요하면 종료
			if(dist[end] > C) break;
//			answer 갱신
			answer = maxCost;
		}
		
		System.out.println(answer);
		
	}
	
	static class Edge{
		int v, w;

		public Edge(int v, int w) {
			this.v = v;
			this.w = w;
		}
		
	}

}