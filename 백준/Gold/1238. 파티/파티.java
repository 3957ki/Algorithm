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
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		
//		목적지로 오는 간선
		List<Edge>[] comeEdges = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) comeEdges[i] = new ArrayList<>();
		
//		돌아가는 간선
		List<Edge>[] goEdges = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) goEdges[i] = new ArrayList<>();
		
		for(int i = 0; i < M; i ++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			comeEdges[end].add(new Edge(start, weight));
			goEdges[start].add(new Edge(end, weight));
		}
		
//		목적지까지 최단거리 저장
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.w-o2.w);
		boolean[] visited = new boolean[N+1];
		int[] comeDst = new int[N+1];
		
		Arrays.fill(comeDst, Integer.MAX_VALUE);		
		pq.add(new Edge(X, 0));
		comeDst[X] = 0;
		
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(visited[now.v]) continue;
			visited[now.v] = true;
			
			for(Edge next : comeEdges[now.v]) {
				if(visited[next.v]) continue;
				if(comeDst[next.v] > comeDst[now.v]+next.w) {
					comeDst[next.v] = comeDst[now.v]+next.w;
					pq.add(new Edge(next.v, comeDst[next.v]));
				}
			}
		}
		
//		목적지에서 돌아가는 최단거리 저장
		pq = new PriorityQueue<>((o1, o2) -> o1.w-o2.w);
		visited = new boolean[N+1];
		int[] goDst = new int[N+1];
		
		Arrays.fill(goDst, Integer.MAX_VALUE);		
		pq.add(new Edge(X, 0));
		goDst[X] = 0;
		
		int answer = 0;
		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			
			if(visited[now.v]) continue;
			visited[now.v] = true;
			
//			왔다 가는 최단거리의 합을 최대로
			answer = Math.max(answer, comeDst[now.v]+goDst[now.v]);
			
			for(Edge next : goEdges[now.v]) {
				if(visited[next.v]) continue;
				if(goDst[next.v] > goDst[now.v]+next.w) {
					goDst[next.v] = goDst[now.v]+next.w;
					pq.add(new Edge(next.v, goDst[next.v]));
				}
			}
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