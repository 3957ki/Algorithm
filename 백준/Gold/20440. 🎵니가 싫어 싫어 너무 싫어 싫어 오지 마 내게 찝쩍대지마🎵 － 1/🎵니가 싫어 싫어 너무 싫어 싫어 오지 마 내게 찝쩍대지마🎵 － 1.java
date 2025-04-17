import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		List<Integer> list = new ArrayList<>();

		Map<Integer, Integer> map = new HashMap<>();

		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if (!map.containsKey(start)) {
				list.add(start);
				map.put(start, 1);
			} else
				map.put(start, map.get(start) + 1);
			if (!map.containsKey(end)) {
				list.add(end);
				map.put(end, -1);
			} else
				map.put(end, map.get(end) - 1);
		}

		Collections.sort(list);

		int max = 0;
		int cur = 0;
		int start = 0;
		int end = 0;
		boolean flag = false;

		for (Integer now : list) {
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

}