import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static long[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());

		tree = new long[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());

		// 구간 업데이트
		for (int i = 1; i <= N; i++) {
			rangeUpdate(i, i, Integer.parseInt(st.nextToken()));
		}

		int M = Integer.parseInt(br.readLine());

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			switch (Integer.parseInt(st.nextToken())) {
				case 1:
					rangeUpdate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()));
					break;
				case 2:
					sb.append(query(Integer.parseInt(st.nextToken()))).append('\n');
					break;
			}
		}

		System.out.println(sb);
	}

	static void update(int num, long diff) {
		while (num <= N) {
			tree[num] += diff;
			num += num & -num;
		}
	}

	static void rangeUpdate(int start, int end, long diff) {
		update(start, diff);
		update(end + 1, -diff);
	}

	// point 조회
	static long query(int num) {
		long sum = 0;
		while (num > 0) {
			sum += tree[num];
			num -= num & -num;
		}

		return sum;
	}
}