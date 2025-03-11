import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		while (t-- > 0) {
			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();

			// 남은거
			Deque<Character> deque = new ArrayDeque<>();

			for (int i = 0; i < N; i++) {
				deque.addLast(str.charAt(i));
			}

			Deque<Node> now = new ArrayDeque<>();
			now.addLast(new Node(null, null));

			while (!deque.isEmpty()) {
				char cur = deque.removeFirst();

				if (cur == 'A') {
					now.addLast(new Node(cur, now.peekLast().ch));
				} else {
					// ABB 상태
					if (now.peekLast().prev != null && now.peekLast().ch == 'B' && now.peekLast().prev == 'A') {
						now.pollLast();
						now.pollLast();
						deque.addFirst('A');
						deque.addFirst('B');
					} else {
						now.addLast(new Node(cur, now.peekLast().ch));
					}
				}

			}
			while (!now.isEmpty()) {
				Character c = now.removeFirst().ch;
				sb.append(c != null ? c : "");
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static class Node {
		Character ch;
		Character prev;

		public Node(Character ch, Character prev) {
			this.ch = ch;
			this.prev = prev;
		}
	}
}