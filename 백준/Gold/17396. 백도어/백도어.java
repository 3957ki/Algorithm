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

		// 시야
		int[] visible = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			visible[i] = num;
		}

		// 간선 리스트
		List<Edge>[] edges = new List[N];
		for (int i = 0; i < N; i++) {
			edges[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			// 둘 중 하나라도 보인다면 못 감
			if (visible[start] == 1 || visible[end] == 1)
				// 둘 다 마지막 지점이 아니어야 함
				if (start != N - 1 && end != N - 1)
					continue;

			edges[start].add(new Edge(end, time));
			edges[end].add(new Edge(start, time));
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		boolean[] visited = new boolean[N];
		long[] dst = new long[N];
		Arrays.fill(dst, Long.MAX_VALUE);
		dst[0] = 0L;
		pq.add(new Edge(0, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (visited[now.v])
				continue;
			visited[now.v] = true;

			// 도착
			if (now.v == N - 1)
				break;

			for (Edge next : edges[now.v]) {
				if (dst[next.v] > dst[now.v] + next.w)
					dst[next.v] = dst[now.v] + next.w;

				pq.add(new Edge(next.v, dst[next.v]));
			}
		}

		System.out.println(dst[N - 1] == Long.MAX_VALUE ? -1 : dst[N - 1]);
	}

	static class Edge implements Comparable<Edge> {
		int v;
		long w;

		public Edge(int v, long w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.w < o.w)
				return -1;
			return 1;
		}
	}
}