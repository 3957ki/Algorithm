import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
//		상 위치
		st = new StringTokenizer(br.readLine());
		int y = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
//		왕 위치
		st = new StringTokenizer(br.readLine());
		int kingY = Integer.parseInt(st.nextToken());
		int kingX = Integer.parseInt(st.nextToken());
		
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[10][9];
		
//		장애물이 있는지 확인하는 방향
		int[] dy1 = {-1, 0, 0, 1, -1, 0, 0, 1};
		int[] dx1 = {0, 1, 1, 0, 0, -1, -1, 0};
		int[] dy2 = {-2, -1, 1, 2, -2, -1, 1, 2};
		int[] dx2 = {1, 2, 2, 1, -1, -2, -2, -1};
//		이동하는 방향
		int[] my = {-3, -2, 2, 3, -3, -2, 2, 3};
		int[] mx = {2, 3, 3, 2, -2, -3, -3, -2};
		
		queue.add(new Node(y, x));
		visited[y][x] = true;
		
		int dst = 0;
		while(!queue.isEmpty()) {
			dst++;
			int L = queue.size();
			
			while(L-- > 0) {
				Node now = queue.poll();
				
				for(int d = 0; d < 8; d++) {
					int Y = now.y+my[d];
					int X = now.x+mx[d];
					if(Y > 9 || Y < 0 || X > 8 || X < 0 || visited[Y][X]) continue;
					if(Y == kingY && X == kingX) {
						System.out.println(dst);
						return;
					}
					if((now.y+dy1[d] == kingY && now.x+dx1[d] == kingX) || (now.y+dy2[d] == kingY && now.x+dx2[d] == kingX)) continue;
					visited[Y][X] = true;
					queue.add(new Node(Y, X));
				}
			}
		}
		
		System.out.println(-1);
	}

	static class Node{
		int y, x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
		
	}
}