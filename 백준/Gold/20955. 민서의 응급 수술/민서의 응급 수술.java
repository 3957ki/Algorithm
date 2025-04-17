import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		Arrays.fill(parent, -1);

		// 연결된 선 수
		int cnt = 0;
		int answer = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			// 사이클이 안생기는거만 연결
			if (union(start, end))
				cnt++;
				// 끊기
			else
				answer++;

		}

		answer += N - 1 - cnt;

		System.out.println(answer);
	}

	static int find(int a) {
		if (parent[a] < 0)
			return a;
		return parent[a] = find(parent[a]);
	}

	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;
		parent[aRoot] += parent[bRoot];
		parent[bRoot] = aRoot;
		return true;
	}
}