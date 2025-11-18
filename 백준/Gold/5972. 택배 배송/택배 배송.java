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
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Node>[] edges = new List[N+1];
		for (int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}

		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edges[start].add(new Node(end, cost));
			edges[end].add(new Node(start, cost));
		}

		int[] dst = new int[N+1];
		boolean[] visited = new boolean[N+1];
		Arrays.fill(dst, Integer.MAX_VALUE);
		dst[1] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		pq.add(new Node(1, 0));

		while(!pq.isEmpty()) {
			Node now = pq.poll();

			if(visited[now.num]) continue;
			visited[now.num] = true;

			if(now.num == N) break;

			for(Node next : edges[now.num]) {
				if(visited[next.num]) continue;

				if(dst[next.num] > dst[now.num] + next.cost) {
					dst[next.num] = dst[now.num] + next.cost;
					pq.add(new Node(next.num, dst[next.num]));
				}
			}
		}

		System.out.println(dst[N]);
    }

	static class Node{
		int num;
		int cost;
		public Node(int num, int cost){
			this.num = num;
			this.cost = cost;
		}
	}
}