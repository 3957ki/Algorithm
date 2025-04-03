import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Set<Integer> set = new HashSet<>();

		int N = Integer.parseInt(br.readLine());
		Integer[] arr = new Integer[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			for (int j = 0; j <= i; j++) {
				set.add(arr[i] + arr[j]);
			}
		}
		Arrays.sort(arr, Collections.reverseOrder());

		for (int i = 0; i < N; i++) {
			int now = arr[i];
			for (int j = i + 1; j < N; j++) {
				if (set.contains(now - arr[j])) {
					System.out.println(now);
					return;
				}

			}
		}

	}
}