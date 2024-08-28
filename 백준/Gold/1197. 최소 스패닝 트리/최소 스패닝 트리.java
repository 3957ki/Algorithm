import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int V = 5;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		
		parents = new int[V+1];
		make();
		
		PriorityQueue<edge> queue = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		
		for(int i = 0 ; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			queue.add(new edge(start, end, weight));
		}
		
		int answer = 0;
		for(int i = 0; i < V-1; i++) {
			edge e = queue.poll();
			if(!union(e.start, e.end)) i--;
			else {
				answer += e.weight;
			}
		}
		System.out.println(answer);
	}
	
	static void make() {
		for(int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(a == parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	static class edge{
		int start, end, weight;

		public edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
	}
}