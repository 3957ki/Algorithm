import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[M][N];
//		1로 채우기
		for(int[] a : map) {
			Arrays.fill(a, 1);
		}
		
//		범위 내에 칸을 0으로
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken())-1;
			int y2 = Integer.parseInt(st.nextToken())-1;
			
			for(int m = y1; m <= y2; m++) {
				for(int n = x1; n <= x2; n++) {
					map[m][n] = 0;
				}
			}
		}
		
		Queue<Node> queue = new ArrayDeque<>();
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		boolean[][] visited = new boolean[M][N];
		
		int cnt =  0;
		List<Integer> list = new ArrayList<>();
//		BFS
		for(int i = 0; i < M; i++) {
			for(int j = 0; j < N; j++) {
				if(visited[i][j] || map[i][j] == 0) continue;
				cnt++;
				int sum = 1;
				visited[i][j] = true;
				queue.add(new Node(i, j));
				
				while(!queue.isEmpty()) {
					Node node = queue.poll();
					for(int d = 0; d < 4; d++) {
						int y = node.y + dy[d];
						int x = node.x + dx[d];
						if(x < 0 || x >= N || y < 0 || y >= M || visited[y][x] || map[y][x] == 0) continue;
						visited[y][x] = true;
						queue.add(new Node(y, x));
						sum++;
					}
				}
				list.add(sum);
			}
		}
		
		Collections.sort(list);
		
		sb.append(cnt).append('\n');
		for(int num : list) {
			sb.append(num).append(' ');
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
