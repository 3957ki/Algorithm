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
		int X = Integer.parseInt(st.nextToken());
		int Y = Integer.parseInt(st.nextToken());

		List<Node>[] edges = new List[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[start].add(new Node(end, weight));
			edges[end].add(new Node(start, weight));
		}

		PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> o1.w - o2.w));

		Node[] dst = new Node[N];
		for (int i = 0; i < N; i++) {
			dst[i] = new Node(i, Integer.MAX_VALUE);
		}
		dst[0].w = 0;
		boolean[] visited = new boolean[N];
		pq.add(new Node(Y, 0));

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (visited[now.v])
				continue;
			visited[now.v] = true;

			for (Node next : edges[now.v]) {
				if (visited[next.v])
					continue;

				if (dst[next.v].w > next.w + dst[now.v].w) {
					dst[next.v].w = next.w + dst[now.v].w;
					pq.add(new Node(next.v, dst[next.v].w));
				}
			}
		}

		Arrays.sort(dst, ((o1, o2) -> o1.w - o2.w));

		int answer = 1;
		int cur = 0;

		for (int i = 0; i < N; i++) {
			if (dst[i].v == Y)
				continue;
			if (dst[i].w == Integer.MAX_VALUE || dst[i].w * 2 > X) {
				answer = -1;
				break;
			}
			if (cur + dst[i].w * 2 <= X) {
				cur += dst[i].w * 2;
			} else {
				cur = dst[i].w * 2;
				answer++;
			}
		}

		System.out.println(answer);
	}

	static class Node {
		int v, w;

		Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}