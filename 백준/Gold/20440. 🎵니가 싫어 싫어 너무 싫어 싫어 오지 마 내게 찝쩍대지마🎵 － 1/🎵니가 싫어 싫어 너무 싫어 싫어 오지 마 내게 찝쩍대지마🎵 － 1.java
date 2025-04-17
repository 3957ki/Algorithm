import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Map<Integer, Integer> map = new TreeMap<>((o1, o2) -> o1 - o2);

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map.put(start, map.getOrDefault(start, 0) + 1);
			map.put(end, map.getOrDefault(end, 0) - 1);
		}

		int max = 0;
		int cur = 0;
		int start = 0;
		int end = 0;
		boolean flag = false;
		for (Map.Entry<Integer, Integer> now : map.entrySet()) {
			cur += now.getValue();
			if (cur > max) {
				max = cur;
				start = now.getKey();
				flag = true;
			} else if (flag && cur < max) {
				end = now.getKey();
				flag = false;
			}
		}

		sb.append(max).append('\n').append(start).append(' ').append(end);
		System.out.println(sb);
	}

	static class Node {
		int start, end;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
}