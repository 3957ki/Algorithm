import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		
		
		Node[] nodes = new Node[V];
		for(int i = 0; i < V; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			nodes[i] = new Node(y, x);
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> (int)(o1.W - o2.W));
		pq.add(new Edge(0, 0)); // 시작 정점 저장
		boolean[] visited = new boolean[V]; // 해당 정점을 간선으로 연결해줬는지 확인
		Edge e;
		
		double cost = 0;
		while(!pq.isEmpty()) {
			e = pq.remove(); // 간선
			if(!visited[e.V]) { // 방문 유무에 따라 그래프에 사이클이 생기는지가 결정
				visited[e.V] = true;
				cost += e.W;
				for(int i = 0; i < V; i++)
					if(!visited[i])
						pq.add(new Edge(i, Math.sqrt(Math.pow(Math.abs(nodes[i].y - nodes[e.V].y), 2) + Math.pow(Math.abs(nodes[i].x - nodes[e.V].x), 2)))); // 방문하지 않은 해당 정점으로 부속된 간선 큐에 저장
			}
		}
		System.out.printf("%.2f", cost);
	}

	static class Node{
		double y, x;

		public Node(double y, double x) {
			this.y = y;
			this.x = x;
		}
		
	}
	
	static class Edge{
		int V;
		double W;

		public Edge(int v, double w) {
			V = v;
			W = w;
		}
		
	}
}