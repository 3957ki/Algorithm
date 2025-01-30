import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int K = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());

		Node[] nodes = new Node[C];

		int low = 0;
		int high = N;
		for (int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			nodes[i] = new Node(y, x);
			low = Math.max(low, y);
		}

		Arrays.sort(nodes, (o1, o2) -> o1.x - o2.x);

		int mid;
		int answer = Integer.MAX_VALUE;
		while (low <= high) {
			mid = (high + low) / 2;

			int prev = 0;
			int cnt = 0;
			for (Node now : nodes) {
				// 이미 덮혔다면 패스
				if (prev > now.x)
					continue;
				prev = now.x + mid;
				cnt++;
			}
			// 개수 초과시 크기 늘리기
			if (cnt > K) {
				low = mid + 1;
			} else {
				high = mid - 1;
				answer = Math.min(answer, mid);
			}
		}

		System.out.println(answer);
	}

	static class Node {
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}