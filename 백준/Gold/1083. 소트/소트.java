import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}

		int S = Integer.parseInt(br.readLine());

		int L = N;
		for (int i = 0; i < N; i++) {
			int max = list.get(0);
			int idx = 0;
			for (int j = 1; j < L; j++) {
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