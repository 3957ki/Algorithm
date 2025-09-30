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
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		List<Node>[] edges = new List[N+1];
		for(int i = 1; i <= N; i++){
			edges[i] = new ArrayList<>();
		}

		while(M-- > 0){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			edges[start].add(new Node(end, cost));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		int[] dst = new int[N+1];
		int[] prev = new int[N+1];
		boolean[] visited = new boolean[N+1];
		Arrays.fill(dst, Integer.MAX_VALUE);
		dst[start] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		pq.add(new Node(start, 0));

		while(!pq.isEmpty()){
			Node now = pq.poll();

			if(visited[now.num]) continue;
			visited[now.num] = true;

			if(now.num == end) break;

			for(Node next : edges[now.num]){
				if(visited[next.num]) continue;
				if(dst[next.num] > now.cost + next.cost){
					dst[next.num] = now.cost + next.cost;
					prev[next.num] = now.num;
					pq.add(new Node(next.num, dst[next.num]));
				}
			}
		}

		List<Integer> path = new ArrayList<>();
		int now = end;
		path.add(now);

		while(prev[now] > 0){
			now = prev[now];
			path.add(now);
		}

		int L = path.size();
		StringBuilder sb = new StringBuilder();
		sb.append(dst[end]).append('\n').append(L).append('\n');

		for(int i = L-1; i >= 0; i--){
			sb.append(path.get(i)).append(' ');
		}

		System.out.println(sb);
    }

	static class Node{
		int num, cost;
		Node(int num, int cost){
			this.num = num;
			this.cost = cost;
		}
	}
}