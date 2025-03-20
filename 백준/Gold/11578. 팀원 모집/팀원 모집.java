import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] arr;
	static int N, M, answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 풀 수 있는 문제 마킹
		arr = new int[M + 1];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken());

			while (cnt-- > 0) {
				int k = Integer.parseInt(st.nextToken()) - 1;
				arr[i] |= 1 << k;
			}
		}

		// 번호, 비트상태, 사람 수
		subset(1, 0, 0);

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	// 부분 집합
	static void subset(int num, int bit, int cnt) {

		if (num > M) {
			if (bit == (1 << N) - 1)
				answer = Math.min(answer, cnt);
			return;
		}

		// 팀원 o
		subset(num + 1, bit | arr[num], cnt + 1);

		// 팀원 x
		subset(num + 1, bit, cnt);
	}
}