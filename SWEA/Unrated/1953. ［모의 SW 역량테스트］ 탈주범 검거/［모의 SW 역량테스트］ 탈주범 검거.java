import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			
			int[][] arr = new int[N][M];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < M; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			Queue<Node> queue = new LinkedList<>();
			boolean[][] visited = new boolean[N][M];
			
			queue.add(new Node(R, C, 1));
			visited[R][C] = true;
			int[] dx = {0, 0, 1, -1};
			int[] dy = {1, -1, 0, 0};
			int answer = 1;
			
			int start = 0;
			int end = 4;
			int num = 1;
			
			while(!queue.isEmpty()) {
				Node node = queue.poll();
				int depth = node.depth;
				if(depth == L) break;
				switch (arr[node.y][node.x]) {
				case 1:
					start = 0;
					end = 4;
					num = 1;
					break;
				case 2:
					start = 0;
					end = 2;
					num = 1;
					break;
				case 3:
					start = 2;
					end = 4;
					num = 1;
					break;
				case 4:
					start = 1;
					end = 3;
					num = 1;
					break;
				case 5:
					start = 0;
					end = 3;
					num = 2;
					break;
				case 6:
					start = 0;
					end = 4;
					num = 3;
					break;
				case 7:
					start = 1;
					end = 4;
					num = 2;
					break;
				default:
					break;
				}
				for(int d = start; d < end; d+=num) {
					int x = node.x+dx[d];
					int y = node.y+dy[d];
					if(x < 0 || x >= M || y < 0 || y >= N) continue;
					if(visited[y][x] || arr[y][x] == 0) continue;
					if(dx[d] == 1 && (arr[y][x] == 2 || arr[y][x] == 4 || arr[y][x] == 5)) continue;
					if(dx[d] == -1 && (arr[y][x] == 2 || arr[y][x] == 6 || arr[y][x] == 7)) continue;
					if(dy[d] == 1 && (arr[y][x] == 3 || arr[y][x] == 5 || arr[y][x] == 6)) continue;
					if(dy[d] == -1 && (arr[y][x] == 3 || arr[y][x] == 4 || arr[y][x] == 7)) continue;
					queue.add(new Node(y, x, depth+1));
					visited[y][x] = true;
					answer++;
				}
			}
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static class Node{
		int x, y, depth;

		public Node(int y, int x, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
		
	}
}