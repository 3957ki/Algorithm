import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int p = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int c = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());

		List<Node>[] edges = new List[p];
		for(int i = 0; i < p; i++){
			edges[i] = new ArrayList<>();
		}

		for(int i = 0; i < w; i++){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			edges[start].add(new Node(end, weight));
			edges[end].add(new Node(start, weight));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.weight - o1.weight);
		int[] dst = new int[p];
		boolean[] visited = new boolean[p];
		dst[c] = 1001;
		pq.add(new Node(c, 1001));

		while(!pq.isEmpty()){
			Node now = pq.poll();

			if(visited[now.num]) continue;
			visited[now.num] = true;

			for(Node next : edges[now.num]){
				if(visited[next.num]) continue;
				if(dst[next.num] < Math.min(dst[now.num], next.weight)){
					dst[next.num] = Math.min(dst[now.num], next.weight);
					pq.add(new Node(next.num, dst[next.num]));
				}
			}
		}

		System.out.println(dst[v]);

    }

	static class Node{
		int num, weight;
		Node(int num, int weight){
			this.num = num;
			this.weight = weight;
		}
	}
}