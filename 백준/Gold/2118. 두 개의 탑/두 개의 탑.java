import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] sum = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			int num = Integer.parseInt(br.readLine());
			sum[i] = sum[i - 1] + num;
		}

		int total = sum[N];

		int answer = 0;

		int prev = 0;
		for (int i = 1; i <= N; i++) {
			int dst = sum[i] - sum[prev];

			// 시계가 작을 때
			if (dst < total - dst) {
				answer = Math.max(dst, answer);
			}

			// 시계가 클 때
			else if (dst > total - dst) {
				answer = Math.max(total - dst, answer);
				prev++;
				i--;
			}

			// 같을 때
			else {
				System.out.println(dst);
				return;
			}
		}
		System.out.println(answer);
	}
}