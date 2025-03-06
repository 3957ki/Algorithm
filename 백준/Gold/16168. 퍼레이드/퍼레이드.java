import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] parents;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 구간 개수
		int[] ahead = new int[N + 1];
		parents = new int[N + 1];
		Arrays.fill(parents, -1);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			ahead[start]++;
			ahead[end]++;
			union(start, end);
		}

		// 홀수점 개수
		int cnt = 0;

		int root = find(1);

		String answer = "NO";
		for (int i = 1; i <= N; i++) {
			// 홀수점이라면
			if ((ahead[i] & 1) == 1)
				cnt++;

			// 부모가 다르다면
			if (find(i) != root) {
				System.out.println(answer);
				return;
			}
		}

		// 홀수점이 2개나 0개면 가능
		if (cnt == 2 || cnt == 0)
			answer = "YES";

		System.out.println(answer);

	}

	static int find(int a) {
		if (parents[a] < 0)
			return a;
		return parents[a] = find(parents[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if (aRoot == bRoot)
			return false;
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}
}