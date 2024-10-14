import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			Queue<Node> queue = new ArrayDeque<>();
			Queue<int[]> temp = new ArrayDeque<>();
			
			Node[][] map = new Node[900][900];
			for(int i = 400; i < 400+N; i++) {				
				st = new StringTokenizer(br.readLine());
				for(int j = 400; j < 400+M; j++) {
					int value = Integer.parseInt(st.nextToken());
					if(value == 0) continue;
					map[i][j] = new Node(i, j, value, value, 0);
					queue.add(map[i][j]);
				}
			}
			
			for(int i = 0; i < K; i++) {
				int L = queue.size();
				
				while(L-- > 0) {
					Node now = queue.poll();
					now.remain--;
//					활성화
					if(now.remain == 0) {
						now.status = 1;
						queue.add(now);
						continue;
					}
//					번식
					else if(now.remain == -1) {
						for(int d = 0; d < 4; d++) {
							int y = now.y + dy[d];
							int x = now.x + dx[d];
//							빈 자리라면
							if(map[y][x] == null) {
								map[y][x] = new Node(y, x, now.value, now.value, 0);
								temp.add(new int[] {y, x});
								continue;
							}
//							비활성 상태이고, 이번 시간에 만들어 진 세포라면
							if(map[y][x].status == 0 && map[y][x].remain == map[y][x].value) {
//								현재 번식하는 값이 더 크다면
								if(map[y][x].value < now.value) {
									map[y][x] = new Node(y, x, now.value, now.value, 0);
								}
								continue;
							}
						}
					}
//					죽음
					if(now.remain + now.value == 0) {
						now.status = 2;
					}
//					안죽음
					else {
						queue.add(now);
					}
				}
				
				while(!temp.isEmpty()) {
					int[] tmp = temp.poll();
					queue.add(map[tmp[0]][tmp[1]]);
				}
				
//				for(int k = 395; k < 410; k++) {
//					for(int l = 395; l < 410; l++) {
//						if(map[k][l] == null || map[k][l].status == 2) {
//							System.out.print("  ");
//						}
//						else {
//							System.out.print(map[k][l].value+" ");
//						}
//					}
//					System.out.println();
//				}
			}
			
			sb.append('#').append(t).append(' ').append(queue.size()).append('\n');
		}
		System.out.println(sb);
	}

	static class Node{
		int y, x, remain, value, status; //0 비활, 1 활, 2 죽음

		public Node(int y, int x, int remain, int value, int status) {
			super();
			this.y = y;
			this.x = x;
			this.remain = remain;
			this.value = value;
			this.status = status;
		}
		
	}
}
