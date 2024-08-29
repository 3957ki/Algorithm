import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		boolean[] visited = new boolean[N+1];
		List<Edge>[] edge = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			edge[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			
			edge[start].add(new Edge(end, weight));
			edge[end].add(new Edge(start, weight));
		}
		
		PriorityQueue<Edge> queue = new PriorityQueue<>((o1, o2) -> {
			return o1.weight - o2.weight;
		});
		queue.add(new Edge(1, 0));
		
		int sum = 0;
		while(!queue.isEmpty()) {
			Edge e = queue.poll();
			if(visited[e.start]) continue;
			visited[e.start] = true;
			sum += e.weight;
			
			for(Edge ed : edge[e.start]) {
				if(!visited[ed.start]) {
					queue.add(ed);
				}
			}
		}
		System.out.println(sum);
	}

	static class Edge{
		int start, weight;

		public Edge(int start, int weight) {
			this.start = start;
			this.weight = weight;
		}
		
	}
}