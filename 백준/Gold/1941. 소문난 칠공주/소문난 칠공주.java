import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Main {

	static int bit, answer;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 5; i++) {
			String str = br.readLine();
			for (int j = 0; j < 5; j++) {
				int val = str.charAt(j) == 'S' ? 1 : 0;
				bit |= val << i * 5 + j;
			}
		}

		comb(0, 0, new HashSet<>());

		System.out.println(answer);
	}

	static void comb(int start, int cnt, Set<Integer> visited) {

		if (cnt == 7) {

			int sum = 0;

			for (int num : visited) {
				sum += (bit & 1 << num) == 0 ? 0 : 1;
			}

			// S가 4명 이상
			if (sum >= 4) {
				BFS(visited);
			}
			return;
		}

		for (int i = start; i < 25; i++) {
			visited.add(i);
			comb(i + 1, cnt + 1, visited);
			visited.remove(i);
		}
	}

	static void BFS(Set<Integer> candidate) {
		Queue<Integer> queue = new ArrayDeque<>();
		Set<Integer> visited = new HashSet<>();
		queue.add(candidate.iterator().next());

		while (!queue.isEmpty()) {
			int now = queue.poll();

			int y = now / 5;
			int x = now % 5;

			for (int d = 0; d < 4; d++) {
				int Y = y + dy[d];
				int X = x + dx[d];
				int next = Y * 5 + X;

				if (Y < 0 || Y > 4 || X < 0 || X > 4 || !candidate.contains(next) || visited.contains(next))
					continue;
				visited.add(next);
				queue.add(next);
			}
		}

		if (visited.size() == 7)
			answer++;
	}
}