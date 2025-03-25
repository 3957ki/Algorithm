import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;
	static long answer = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		func(0, N - 1, 0);

		System.out.println(answer);
	}

	static void func(int start, int end, long sum) {

		if (start == end) {
			answer = Math.max(answer, sum + arr[start]);
			return;
		}

		int mid = (end - start + 1) / 2;

		// 왼쪽
		func(start + mid, end, sum + calc1(start, start + mid - 1));

		// 오른쪽
		func(start, start + mid - 1, sum + calc1(start + mid, end));
	}

	static int calc1(int start, int end) {

		int a = arr[start];
		for (int i = start + 1; i <= end; i++) {
			a = calc2(a, arr[i]);
		}

		return a;
	}

	static int calc2(int a, int b) {
		int temp = a;
		if (b > a) {
			a = b;
			b = temp;
		}

		while (b != 0) {
			temp = b;
			b = a % b;
			a = temp;
		}
		return a;
	}
}