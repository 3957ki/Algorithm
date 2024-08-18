import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N][N];
			List<int[]> list = new ArrayList<>();
			int max = 0;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if(max < arr[i][j]) {
						max = arr[i][j];
						list.clear();
						list.add(new int[] {i, j});
					}
					else if(max == arr[i][j]) {
						list.add(new int[] {i, j});
					}
				}
			}
			
			
			answer = 0;
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					for(int depth = 0; depth <= K; depth++) {
						if(arr[r][c] < depth) continue;
						arr[r][c] -= depth;
						for(int[] start : list) {
							boolean[][] visited = new boolean[N][N];
							visited[start[0]][start[1]] = true;
							DFS(start[0], start[1], 1, visited, arr, N);
						}
						arr[r][c] += depth;
					}
				}
			}
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static void DFS(int i, int j, int distance, boolean[][] visited, int[][] arr, int N) {
		
		for(int d = 0; d < 4; d++) {
			int y = i+dy[d];
			int x = j+dx[d];
			if(x < 0 || x >= N || y < 0 || y >= N) continue;
			if(visited[y][x]) continue;
			if(arr[y][x] >= arr[i][j]) continue;
			visited[y][x] = true;
			DFS(y, x, distance+1, visited, arr, N);
			visited[y][x] = false;
		}
		answer = Math.max(answer, distance);
	}
	
}