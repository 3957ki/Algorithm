import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		int K = 0;
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[i] == M)
				K = i;
		}

		Map<Integer, Integer> leftMap = new HashMap<>();
		Map<Integer, Integer> rightMap = new HashMap<>();
		leftMap.put(0, 1);
		rightMap.put(0, 1);

		int cur = 0;
		for (int i = K - 1; i >= 0; i--) {
			if (arr[i] < M)
				cur--;
			else
				cur++;

			leftMap.put(cur, leftMap.getOrDefault(cur, 0) + 1);
		}
		cur = 0;
		for (int i = K + 1; i < N; i++) {
			if (arr[i] < M)
				cur--;
			else
				cur++;
			rightMap.put(cur, rightMap.getOrDefault(cur, 0) + 1);
		}

		int answer = 0;
		for (Map.Entry<Integer, Integer> now : leftMap.entrySet()) {
			if (rightMap.containsKey(now.getKey() * (-1))) {
				answer += now.getValue() * rightMap.get(now.getKey() * (-1));
			}
		}

		System.out.println(answer);
	}
}