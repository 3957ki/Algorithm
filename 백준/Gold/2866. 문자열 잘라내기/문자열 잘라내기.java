import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] matrix = new char[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			matrix[i] = str.toCharArray();
		}

		Set<Long>[] set = new Set[N];
		for (int i = 0; i < N; i++) {
			set[i] = new HashSet<>();
		}

		int answer = N;

		for (int j = 0; j < M; j++) {
			long now = 0L;
			for (int i = N - 1; i >= 0; i--) {
				now += now * 26 + (matrix[i][j] - 'a' + 1);

				// 현재 중복 행 이상이면 패스
				if (i >= answer)
					continue;

				// 중복 되었다면
				if (set[i].contains(now)) {
					answer = i;
				} else {
					set[i].add(now);
				}
			}
		}

		System.out.println(answer - 1);
	}
}