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

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());

		List<Node>[] edges = new List[V+1];
		for(int i = 1; i <= V; i++){
			edges[i] = new ArrayList<>();
		}

		while(E-- > 0) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			edges[start].add(new Node(end, cost, end == P ? 1 : 0));
			edges[end].add(new Node(start, cost, start == P ? 1 : 0));
		}

		// 다익스트라 (거리가 같으면 건우를 거친 경로를 우선)
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost == o2.cost ? o2.flag - o1.flag : o1.cost - o2.cost);
		int[] dst = new int[V+1];
		Arrays.fill(dst, Integer.MAX_VALUE);
		boolean[] visited = new boolean[V+1];

		pq.add(new Node(1, 0, P == 1 ? 1 : 0));
		dst[1] = 0;

		String answer = "GOOD BYE";

		while(!pq.isEmpty()){
			Node now = pq.poll();

			if(visited[now.num]) continue;
			visited[now.num] = true;

			if(now.num == V){
				if(now.flag == 1) answer = "SAVE HIM";
				break;
			}

			for(Node next : edges[now.num]){
				if(visited[next.num]) continue;

				int value = now.cost + next.cost;
				if(dst[next.num] > value){
					dst[next.num] = value;
					pq.add(new Node(next.num, value, now.flag | next.flag));
				}
				else if(dst[next.num] == value && (now.flag | next.flag) == 1){
					pq.add(new Node(next.num, value, now.flag | next.flag));
				}
			}
		}

		System.out.println(answer);

    }

	static class Node{
		int num, cost, flag;

		public Node(int num, int cost, int flag){
			this.num = num;
			this.cost = cost;
			this.flag = flag;
		}
	}
}