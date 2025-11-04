import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static List<Node>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		while(T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			edges = new List[N+1];
			for(int i = 1; i <= N; i++) {
				edges[i] = new ArrayList<>();
			}

			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				edges[start].add(new Node(end, cost));
				edges[end].add(new Node(start, cost));
			}

			int answer = 0;
			for(Node now : edges[1]){
				answer += DFS(now.num, 1, now.cost);
			}

			sb.append(answer).append('\n');
		}

		System.out.println(sb);
    }

	static int DFS(int now, int prev, int cost){

		// 루팡
		if(edges[now].size() == 1){
			return edges[now].get(0).cost;
		}

		int sum = 0;
		for(Node next : edges[now]){
			if(next.num == prev) continue;
			sum += DFS(next.num, now, next.cost);
		}
		return Math.min(sum, cost);
	}

	static class Node{
		int num, cost;
		Node(int num, int cost){
			this.num = num;
			this.cost = cost;
		}
	}
}