import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		PriorityQueue<Integer> pq = new PriorityQueue<>();

		int max = 0;
		int min = Integer.MAX_VALUE;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			max = Math.max(max, num);
			min = Math.min(min, num);
			pq.add(num);
		}

		long answer = max - min;
		int cut = max;

		while (true) {
			Integer now = pq.poll();
			answer = Math.min(answer, max - now);
			if (now >= cut) {
				break;
			}
			max = Math.max(max, now * 2);
			pq.add(now * 2);
		}

		System.out.println(answer);

	}
}