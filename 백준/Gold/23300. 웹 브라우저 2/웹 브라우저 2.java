import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		Deque<Integer> leftStack = new ArrayDeque<>();
		Deque<Integer> rightStack = new ArrayDeque<>();

		int now = -1;
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			char cmd = st.nextToken().charAt(0);
			switch (cmd) {
				case 'B':
					if (!leftStack.isEmpty()) {
						if (now != -1)
							rightStack.push(now);
						now = leftStack.pop();
					}
					break;
				case 'F':
					if (!rightStack.isEmpty()) {
						if (now != -1)
							leftStack.push(now);
						now = rightStack.pop();
					}
					break;
				case 'C':
					int L = leftStack.size();
					int prev = -1;
					while (L-- > 0) {
						int temp = leftStack.pollLast();
						if (temp != prev) {
							leftStack.push(temp);
							prev = temp;
						}
					}
					break;
				case 'A':
					int num = Integer.parseInt(st.nextToken());
					if (now != -1)
						leftStack.push(now);
					rightStack.clear();
					now = num;
					break;
			}
		}

		sb.append(now).append('\n');
		if (!leftStack.isEmpty()) {
			while (!leftStack.isEmpty()) {
				sb.append(leftStack.pop()).append(' ');
			}
		} else
			sb.append(-1);

		sb.append('\n');

		if (!rightStack.isEmpty()) {
			while (!rightStack.isEmpty()) {
				sb.append(rightStack.pop()).append(' ');
			}
		} else
			sb.append(-1);
		System.out.println(sb);

	}
}