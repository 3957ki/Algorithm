import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[][] arr = new char[8][8];
		for(int i = 0; i < 8; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		ArrayDeque<Node> queue = new ArrayDeque<>();
		int[] dx = {0, 0, 1, -1, 1, 1, -1, -1, 0};
		int[] dy = {1, -1, 0, 0, 1, -1, 1, -1, 0};
		queue.add(new Node(7, 0, 0));
		int time = 0;
//		BFS
		while(!queue.isEmpty()) {
			Node node = queue.pollFirst();
			int depth = node.depth;
//			시간이 한차례 지난 노드라면 벽 한칸씩 내려오기
			if(time != depth) {
				time = depth;
				for(int i = 6; i >= 0; i--) {
					for(int j = 7; j >= 0; j--) {
						arr[i+1][j] = arr[i][j];
						arr[i][j] = '.';
					}
				}
			}
//			9방 탐색
			for(int d = 0; d < 9; d++) {
				int x = node.x + dx[d];
				int y = node.y + dy[d];
				if(x < 0 || x > 7 || y < 0 || y > 7) continue;
//				최상위층에 도달하거나, 8초가 지난다면 true;
				if(y == 0 || depth == 8) {
					System.out.println(1);
					return;
				}
//				탐색한 노드가 벽이 아니고, 노드 바로 위가 벽이 아닐 때 1초 증가 후 add
				if(arr[y][x] == '.' && arr[y-1][x] == '.') {
					queue.addLast(new Node(y, x, depth+1));
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
