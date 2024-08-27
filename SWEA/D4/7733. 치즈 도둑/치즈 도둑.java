import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			int[][] map = new int[N][N];
			TreeSet<Integer> set = new TreeSet<>();
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					set.add(map[i][j]);
				}
			}
			
			Queue<Node> queue = new ArrayDeque<>();
			int[] dx = {0, 0, 1, -1};
			int[] dy = {1, -1, 0, 0};
			
			int answer = 1;
			for(int num : set) {
				int cnt = 0;
				boolean[][] visited = new boolean[N][N];
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(map[i][j] <= num || visited[i][j]) continue;
						visited[i][j] = true;
						queue.add(new Node(i, j));
						cnt++;
						while(!queue.isEmpty()) {
							Node node = queue.poll();
							for(int d = 0; d < 4; d++) {
								int y = node.y+dy[d];
								int x = node.x+dx[d];
								if(x < 0 || x >= N || y < 0 || y >= N || visited[y][x] || map[y][x] <= num) continue;
								visited[y][x] = true;
								queue.add(new Node(y, x));
							}
						}
						
					}
				}
				answer = Math.max(answer, cnt);
			}
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
		
	}
}