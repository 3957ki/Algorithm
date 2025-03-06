import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static int[] arr;
	static int[] seg;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			arr = new int[N + 1];
			seg = new int[N * 4];

			// 제일 왼쪽과 제일 오른쪽
			Map<Integer, int[]> map = new HashMap<>();

			// 숫자별 구간 등록
			for (int i = 1; i <= N; i++) {
				int num = Integer.parseInt(st.nextToken());
				arr[i] = num;

				int[] now = map.getOrDefault(num, new int[] {200000, 1});

				int left = Math.min(now[0], i);
				int right = Math.max(now[1], i);

				map.put(num, new int[] {left, right});
			}

			// 구간 최대 초기화
			init(1, N, 1);

			String answer = "Yes";

			for (Integer num : map.keySet()) {
				int[] now = map.get(num);

				// 구간 최대값이 자신보다 크면 No
				if (find(now[0], now[1], 1, N, 1) > num) {
					answer = "No";
					break;
				}
			}

			sb.append(answer).append('\n');

		}
		System.out.println(sb);
	}

	static int init(int left, int right, int index) {

		if (left == right)
			return seg[index] = arr[left];

		int mid = (left + right) / 2;

		return seg[index] = Math.max(init(left, mid, index << 1), init(mid + 1, right, index << 1 | 1));
	}

	static int find(int start, int end, int left, int right, int index) {

		if (left > end || right < start)
			return 0;
		if (left >= start && right <= end)
			return seg[index];
		int mid = (left + right) / 2;

		return Math.max(find(start, end, left, mid, index << 1), find(start, end, mid + 1, right, index << 1 | 1));
	}
}