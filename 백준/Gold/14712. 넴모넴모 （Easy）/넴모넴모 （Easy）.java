import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int M, L, answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = N * M;
		answer = 0;

		boolean[][] visited = new boolean[N+1][M+1];
		DFS(visited, 0);

		System.out.println(answer);
    }

	static void DFS(boolean[][] visited, int idx){
		if(idx == L){
			answer++;
			return;
		}

		int y = idx / M + 1;
		int x = idx % M + 1;

		DFS(visited, idx+1);

		if(!visited[y-1][x-1] || !visited[y-1][x] || !visited[y][x-1]){
			visited[y][x] = true;
			DFS(visited, idx+1);
			visited[y][x] = false;
		}
	}
}