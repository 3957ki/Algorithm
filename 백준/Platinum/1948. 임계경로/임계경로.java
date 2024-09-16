import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
//		정방향, 역방향 간선 리스트
		List<Edge>[] edges = new List[N+1];
		List<Edge>[] r_edges = new List[N+1];
		for(int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
			r_edges[i] = new ArrayList<>();
		}
//		진입차수, 소요시간
		int[] ahead = new int[N+1];
		int[] time = new int[N+1];
		
		StringTokenizer st;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edges[start].add(new Edge(end, weight));
			r_edges[end].add(new Edge(start, weight));
			ahead[end]++;
		}
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		
//		정방향 위상정렬로 소요시간 구하기
		queue.add(start);
		
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(Edge next : edges[now]) {
				time[next.v] = Math.max(time[next.v], next.w + time[now]);
				
				if(--ahead[next.v] == 0) {
					queue.add(next.v);
				}
			}
		}
		
//		역방향 위상정렬로 필수 도로 구하기
		boolean[] visited = new boolean[N+1];
		queue.add(end);
		visited[end] = true;
		
		int answer = 0;
		while(!queue.isEmpty()) {
			int now = queue.poll();
			
			for(Edge next : r_edges[now]) {
//				(다음 노드까지 걸리는 시간 + 다음 노드 사이의 시간)이 현재 노드까지 걸리는 시간과 같을 때만 answer++
				if(time[next.v]+next.w != time[now]) continue;
				answer++;
//				방문하지 않았다면 큐에 넣기
				if(!visited[next.v]) {
					queue.add(next.v);
					visited[next.v] = true;
				}
			}
		}
		
		System.out.println(time[end]);
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