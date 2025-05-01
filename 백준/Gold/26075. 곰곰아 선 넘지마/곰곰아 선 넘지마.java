import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr1 = new int[M];
		int[] arr2 = new int[M];

		String s1 = br.readLine();
		String s2 = br.readLine();

		int idx1 = 0;
		int idx2 = 0;

		for (int i = 0; i < N + M; i++) {
			if (s1.charAt(i) == '1')
				arr1[idx1++] = i;
			if (s2.charAt(i) == '1')
				arr2[idx2++] = i;
		}

		long sum = 0;
		for (int i = 0; i < M; i++) {
			sum += Math.abs(arr1[i] - arr2[i]);
		}
		long X = sum / 2;
		long Y = sum - X;

		System.out.println(X * X + Y * Y);
	}
}