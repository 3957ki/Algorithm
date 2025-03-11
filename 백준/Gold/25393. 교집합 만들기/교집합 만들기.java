import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());

		Map<Integer, Node> left = new HashMap<>();
		Map<Integer, Node> right = new HashMap<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			if (!right.containsKey(start))
				right.put(start, new Node(start));

			Node r = right.get(start);
			r.nums.add(end);
			r.value = Math.max(r.value, end);

			if (!left.containsKey(end))
				left.put(end, new Node(end));

			Node l = left.get(end);
			l.nums.add(start);
			l.value = Math.min(l.value, start);
		}

		int M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			if (!right.containsKey(start) || !left.containsKey(end)) {
				sb.append(-1).append('\n');
			} else if (right.get(start).nums.contains(end)) {
				sb.append(1).append('\n');
			} else if (right.get(start).value > end && left.get(end).value < start) {
				sb.append(2).append('\n');
			} else {
				sb.append(-1).append('\n');
			}
		}
		System.out.println(sb);
	}

	static class Node {
		int value;
		Set<Integer> nums = new HashSet<>();

		public Node(int value) {
			this.value = value;
		}
	}
}