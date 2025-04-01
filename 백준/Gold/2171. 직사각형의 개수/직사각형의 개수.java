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
		int N = Integer.parseInt(br.readLine());

		Map<Integer, Set<Integer>> map = new HashMap<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int Y = Integer.parseInt(st.nextToken());
			if (!map.containsKey(X)) {
				map.put(X, new HashSet<>());
			}
			map.get(X).add(Y);
		}

		Integer[] arr = map.keySet().toArray(new Integer[0]);
		int L = arr.length;

		int answer = 0;

		// x1
		for (int i = 0; i < L; i++) {
			Set<Integer> now = map.get(arr[i]);
			Integer[] next = now.toArray(new Integer[0]);
			int K = next.length;

			for (int j = i + 1; j < L; j++) {
				for (int l = 0; l < K; l++) {
					int y1 = next[l];
					for (int k = l + 1; k < K; k++) {
						int y2 = next[k];

						if (map.get(arr[j]).contains(y1) && map.get(arr[j]).contains(y2))
							answer++;

					}
				}
			}

		}

		System.out.println(answer);

	}
}