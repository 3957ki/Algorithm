import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		List<Integer>[] list = new List[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		int[] parents = new int[N + 1];
		int[] scores = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 2; i <= N; i++) {
			parents[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 2; i <= N; i++) {
			list[parents[i]].add(i);
		}

		// 내림차순 pq
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.value - o1.value);

		pq.add(new Node(1, scores[1]));
		long sum = 0;

		for (int i = 1; i <= N; i++) {
			Node now = pq.poll();
			sum += now.value;

			for (Integer next : list[now.num]) {
				pq.add(new Node(next, scores[next]));
			}
			sb.append(sum).append('\n');
		}
		System.out.println(sb);
	}

	static class Node {
		int num;
		int value;

		public Node(int num, int value) {
			this.num = num;
			this.value = value;
		}
	}
}