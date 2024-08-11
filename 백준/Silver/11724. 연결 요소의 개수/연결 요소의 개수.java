import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[] visited;
	static boolean[][] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new boolean[N+1][N+1];
		visited = new boolean[N+1];
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			arr[start][end] = true;
			arr[end][start] = true;
		}
		int answer = 0;
		for(int i = 1; i <= N; i++) {
			if(!visited[i]) {
				visited[i] = true;
				DFS(i);
				answer++;
			}
		}
		System.out.println(answer);
	}
	static void DFS(int start) {
		for(int i = 1; i <= N; i++) {
			if(!visited[i] && arr[start][i]) {
				visited[i] = true;
				arr[start][i] = false;
				arr[i][start] = false;
				DFS(i);
			}
		}
	}
}
