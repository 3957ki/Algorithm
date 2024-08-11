import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] arr = new char[8][8];
		for(int i = 0; i < 8; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		Queue<Node> queue = new LinkedList<>();
		int[] dx = {0, 0, 1, -1, 1, 1, -1, -1, 0};
		int[] dy = {1, -1, 0, 0, 1, -1, 1, -1, 0};
		queue.add(new Node(7, 0, 0));
		int time = 0;
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int depth = node.depth;
			if(time != depth) {
				time = depth;
				for(int i = 6; i >= 0; i--) {
					for(int j = 7; j >= 0; j--) {
						arr[i+1][j] = arr[i][j];
						arr[i][j] = '.';
					}
				}
			}
			for(int d = 0; d < 9; d++) {
				int x = node.x + dx[d];
				int y = node.y + dy[d];
				if(x < 0 || x > 7 || y < 0 || y > 7) continue;
				if(y == 0 || depth == 8) {
					System.out.println(1);
					return;
				}
				if(arr[y][x] == '.' && arr[y-1][x] == '.') {
					queue.add(new Node(y, x, depth+1));
				}
			}
		}
		System.out.println(0);
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
