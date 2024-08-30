import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M, cnt0, L, answer = 0;
	static List<Node> cctv = new ArrayList<>();
	static int[][] map;
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
//				사각지대 개수세기, cctv리스트 추가
				if(map[i][j] == 0) cnt0++;
				else if(map[i][j] < 6) {
					cctv.add(new Node(i, j, map[i][j]));
				}
			}
		}
		L = cctv.size();
		DFS(0, 0);
		System.out.println(cnt0-answer);
	}

	static void DFS(int cnt, int sum) {
		if(cnt == L) {
			answer = Math.max(answer, sum);
			return;
		}
		
		int[][] temp = new int[N][M];
		for(int i = 0; i < N; i++) {
			temp[i] = Arrays.copyOf(map[i], M);
		}
		
		Node node = cctv.get(cnt);
		int y = node.y;
		int x = node.x;
		int s = 0;
		
		switch (map[y][x]) {
		case 1:
			for(int d = 0; d < 4; d++) {
//				한쪽 방향 처리
				y += dy[d];
				x += dx[d];
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					if(map[y][x] == 0) {
						map[y][x] = -1;
						s++;
					}
					y += dy[d];
					x += dx[d];
				}
				
				DFS(cnt+1, sum + s);
				s = 0;
				
//				롤백
				y = node.y;
				x = node.x;
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					map[y][x] = temp[y][x];
					y += dy[d];
					x += dx[d];
				}
				y = node.y;
				x = node.x;
			}
			break;
		case 2:
			for(int d = 0; d < 4; d += 2) {
//				한쪽 방향 처리
				y += dy[d];
				x += dx[d];
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					if(map[y][x] == 0) {
						map[y][x] = -1;
						s++;
					}
					y += dy[d];
					x += dx[d];
				}
				y = node.y;
				x = node.x;
//				반대쪽 방향 처리
				y += dy[d+1];
				x += dx[d+1];
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					if(map[y][x] == 0) {
						map[y][x] = -1;
						s++;
					}
					y += dy[d+1];
					x += dx[d+1];
				}
				
				DFS(cnt+1, sum + s);
				s = 0;
				
//				롤백
				y = node.y;
				x = node.x;
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					map[y][x] = temp[y][x];
					y += dy[d];
					x += dx[d];
				}
				y = node.y;
				x = node.x;
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					map[y][x] = temp[y][x];
					y += dy[d+1];
					x += dx[d+1];
				}
				y = node.y;
				x = node.x;
			}
			break;
		case 3:
			for(int d = 0; d < 2; d++) {
//				한쪽 방향 처리
				y += dy[d];
				x += dx[d];
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					if(map[y][x] == 0) {
						map[y][x] = -1;
						s++;
					}
					y += dy[d];
					x += dx[d];
				}
				y = node.y;
				x = node.x;
//				다음 방향 처리
				y += dy[2];
				x += dx[2];
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					if(map[y][x] == 0) {
						map[y][x] = -1;
						s++;
					}
					y += dy[2];
					x += dx[2];
				}
				
				DFS(cnt+1, sum + s);
				s = 0;
				
//				롤백
				y = node.y;
				x = node.x;
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					map[y][x] = temp[y][x];
					y += dy[d];
					x += dx[d];
				}
				y = node.y;
				x = node.x;
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					map[y][x] = temp[y][x];
					y += dy[2];
					x += dx[2];
				}
				y = node.y;
				x = node.x;
				
//				한쪽 방향 처리
				y += dy[d];
				x += dx[d];
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					if(map[y][x] == 0) {
						map[y][x] = -1;
						s++;
					}
					y += dy[d];
					x += dx[d];
				}
				y = node.y;
				x = node.x;
//				다음 방향 처리
				y += dy[3];
				x += dx[3];
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					if(map[y][x] == 0) {
						map[y][x] = -1;
						s++;
					}
					y += dy[3];
					x += dx[3];
				}
				
				DFS(cnt+1, sum + s);
				s = 0;
				
//				롤백
				y = node.y;
				x = node.x;
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					map[y][x] = temp[y][x];
					y += dy[d];
					x += dx[d];
				}
				y = node.y;
				x = node.x;
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					map[y][x] = temp[y][x];
					y += dy[3];
					x += dx[3];
				}
				y = node.y;
				x = node.x;
			}
			break;
		case 4:
			for(int d = 0; d < 4; d++) {
//				d 방향을 제외하고 모두 처리
				y += dy[(d+1)%4];
				x += dx[(d+1)%4];
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					if(map[y][x] == 0) {
						map[y][x] = -1;
						s++;
					}
					y += dy[(d+1)%4];
					x += dx[(d+1)%4];
				}
				y = node.y;
				x = node.x;

				y += dy[(d+2)%4];
				x += dx[(d+2)%4];
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					if(map[y][x] == 0) {
						map[y][x] = -1;
						s++;
					}
					y += dy[(d+2)%4];
					x += dx[(d+2)%4];
				}
				y = node.y;
				x = node.x;
				
				y += dy[(d+3)%4];
				x += dx[(d+3)%4];
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					if(map[y][x] == 0) {
						map[y][x] = -1;
						s++;
					}
					y += dy[(d+3)%4];
					x += dx[(d+3)%4];
				}
				
				DFS(cnt+1, sum + s);
				s = 0;
				
//				롤백
				y = node.y;
				x = node.x;
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					map[y][x] = temp[y][x];
					y += dy[(d+1)%4];
					x += dx[(d+1)%4];
				}
				y = node.y;
				x = node.x;
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					map[y][x] = temp[y][x];
					y += dy[(d+2)%4];
					x += dx[(d+2)%4];
				}
				y = node.y;
				x = node.x;
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					map[y][x] = temp[y][x];
					y += dy[(d+3)%4];
					x += dx[(d+3)%4];
				}
				y = node.y;
				x = node.x;
			}
			break;
		case 5:
//			모든 방향 처리
			for(int d = 0; d < 4; d++) {
				y += dy[d];
				x += dx[d];
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					if(map[y][x] == 0) {
						map[y][x] = -1;
						s++;
					}
					y += dy[d];
					x += dx[d];
				}
				y = node.y;
				x = node.x;
			}
			
			DFS(cnt+1, sum + s);
			s = 0;
			
//			롤백
			for(int d = 0; d < 4; d++) {
				y += dy[d];
				x += dx[d];
				while(y >= 0 && y < N && x >= 0 && x < M && map[y][x] != 6) {
					map[y][x] = temp[y][x];
					y += dy[d];
					x += dx[d];
				}
				y = node.y;
				x = node.x;
			}
			break;
		}
	}
	
	static class Node{
		int y, x, num;

		public Node(int y, int x, int num) {
			this.y = y;
			this.x = x;
			this.num = num;
		}
		
	}
}