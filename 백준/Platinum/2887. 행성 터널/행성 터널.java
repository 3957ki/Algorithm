import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		Node[] NodeList = new Node[N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			NodeList[i] = new Node(i, x, y, z);
		}
		parents = new int[N];
		make();
		PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
		
//		x를 기준으로 정렬
		Arrays.sort(NodeList, (o1, o2) -> o1.x - o2.x);
		for(int i = 0; i < N-1; i++) {
			pq.add(new Edge(NodeList[i].num, NodeList[i+1].num, Math.abs(NodeList[i].x - NodeList[i+1].x)));
		}
//		y를 기준으로 정렬
		Arrays.sort(NodeList, (o1, o2) -> o1.y - o2.y);
		for(int i = 0; i < N-1; i++) {
			pq.add(new Edge(NodeList[i].num, NodeList[i+1].num, Math.abs(NodeList[i].y - NodeList[i+1].y)));
		}
//		z를 기준으로 정렬
		Arrays.sort(NodeList, (o1, o2) -> o1.z - o2.z);
		for(int i = 0; i < N-1; i++) {
			pq.add(new Edge(NodeList[i].num, NodeList[i+1].num, Math.abs(NodeList[i].z - NodeList[i+1].z)));
		}
		
		int answer = 0;
		int cnt = 0;
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			if(union(e.start, e.end)) {
				answer += e.weight;
				if(++cnt == N-1) break;
			}
		}
		
		System.out.println(answer);
	}

	static void make() {
		for(int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
	
	static class Node{
		int num, x, y, z;

		public Node(int num, int x, int y, int z) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.z = z;
		}
		
	}
	
	static class Edge{
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
	}
}