import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Integer[] arr = new Integer[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr, Collections.reverseOrder());
		PriorityQueue<Integer> pq = new PriorityQueue<>();

		int answer = arr[0];

		for (int i = 0; i < N; i++) {
			int now = arr[i];
			if (!pq.isEmpty() && pq.size() == M) {
				now += pq.poll();
			}
			pq.add(now);
			answer = Math.max(answer, now);
		}
		System.out.println(answer);
	}
}