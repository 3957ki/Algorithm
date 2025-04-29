import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		List<Integer> list = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}

		int S = Integer.parseInt(br.readLine());

		int L = N;
		for (int i = 0; i < N; i++) {

			// 현재 최댓값과 인덱스
			int max = list.get(0);
			int idx = 0;

			for (int j = 1; j < L; j++) {
				// 교환 횟수가 남는 경우만
				if (j <= S) {
					int now = list.get(j);
					if (now > max) {
						max = now;
						idx = j;
					}
				} else
					break;
			}
			sb.append(max).append(' ');
			list.remove(idx);
			L--;
			S -= idx;
		}

		System.out.println(sb);
	}
}