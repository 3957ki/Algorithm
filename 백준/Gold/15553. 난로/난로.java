import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		List<Integer> list = new ArrayList<>();

		int cnt = 1;
		int time = N;
		int prev = Integer.parseInt(br.readLine());
		for (int i = 1; i < N; i++) {
			int now = Integer.parseInt(br.readLine());
			// 연속되지 않으면 성냥 소모
			if (now != prev + 1) {
				cnt++;
				list.add(now - prev - 1);
			}
			prev = now;
		}

		Collections.sort(list);

		// 초과한 성냥 수
		int diff = cnt - K;

		for (int i = 0; i < diff; i++) {
			time += list.get(i);
		}

		System.out.println(time);
	}
}