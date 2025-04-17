import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		Map<Integer, Integer> map = new HashMap<>();

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			map.put(start, map.getOrDefault(start, 0) + 1);
			map.put(end, map.getOrDefault(end, 0) - 1);
		}

		Integer[] arr = map.keySet().toArray(new Integer[0]);
		Arrays.sort(arr);

		int max = 0;
		int cur = 0;
		int start = 0;
		int end = 0;
		boolean flag = false;
		for (Integer now : arr) {
			cur += map.get(now);
			if (cur > max) {
				max = cur;
				start = now;
				flag = true;
			} else if (flag && cur < max) {
				end = now;
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