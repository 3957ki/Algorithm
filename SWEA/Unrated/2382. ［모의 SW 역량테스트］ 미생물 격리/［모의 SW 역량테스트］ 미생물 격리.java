import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, virusCnt;
	static Queue<Node>[][] map;
	static Queue<Node>[][] temp;
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, -1, 1, 0, 0};
	static int[] delta = {0, 2, 1, 4, 3};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			map = new ArrayDeque[N][N];
			temp = new ArrayDeque[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = new ArrayDeque<>();
					temp[i][j] = new ArrayDeque<>();
				}
			}
//			총 미생물 개수
			virusCnt = 0;
			
			for(int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int virus = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				map[y][x].add(new Node(virus, dir));
				virusCnt += virus;
			}
			
			for(int i = 0; i < M; i++) {
//				M번 이동
				move();
			}
			sb.append('#').append(t).append(' ').append(virusCnt).append('\n');
		}
		System.out.println(sb);
	}
	
	static void move() {
//		군집이 있으면 이동시키기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j].isEmpty()) continue;
				while(!map[i][j].isEmpty()) {
					Node node = map[i][j].poll();
					int y = i + dy[node.dir];
					int x = j + dx[node.dir];
					if(x == 0 || x == N-1 || y == 0 || y == N-1) {
//						테두리에 오면 미생물 절반 감소하고 방향 바꾸기
						virusCnt -= (node.virus - node.virus/2);
						node.virus /= 2;
						node.dir = delta[node.dir];
					}
//					미생물 개수가 0이면 군집 표시하지 않기
					if(node.virus == 0) continue;
//					임시 map에 표시
					temp[y][x].add(node);
				}
			}
		}
		
//		겹친 군집 합치기
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(temp[i][j].isEmpty()) continue;
				int maxVirus = 0;
				int dir = 0;
				int sumVirus = 0;
				while(!temp[i][j].isEmpty()) {
					Node node = temp[i][j].poll();
//					미생물 합치고, 가장 큰 미생물을 가진 군집의 방향으로 결정
					if(maxVirus < node.virus) {
						maxVirus = node.virus;
						dir = node.dir;
					}
					sumVirus += node.virus;
				}
//				map에 표시
				map[i][j].add(new Node(sumVirus, dir));
			}
		}
	}

	static class Node{
		int virus, dir;

		public Node(int virus, int dir) {
			this.virus = virus;
			this.dir = dir;
		}
		
	}
}