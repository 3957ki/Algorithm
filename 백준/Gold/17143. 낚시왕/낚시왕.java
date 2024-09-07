import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int[] dx = {0, 0, 1, -1};	//상하우좌
	static int[] dy = {-1, 1, 0, 0};
	static int N, M, answer = 0;
	static PriorityQueue<Shark>[][] map, temp, t;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		
		map = new PriorityQueue[N][M];
		temp = new PriorityQueue[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				map[i][j] = new PriorityQueue<>((o1, o2) -> o2.z - o1.z);
				temp[i][j] = new PriorityQueue<>((o1, o2) -> o2.z - o1.z);
			}
		}
		
		for(int i = 0; i < C; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken())-1;
			int x = Integer.parseInt(st.nextToken())-1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken())-1;
			int z = Integer.parseInt(st.nextToken());
			map[y][x].add(new Shark(s, d, z));
		}
		
//		사람 이동
		for(int i = 0; i < M; i++) {
			play(i);
		}
		
		System.out.println(answer);
		
	}

	static void play(int start) {
		
//		상어 잡기
		for(int i = 0; i < N; i++) {
			if(map[i][start].isEmpty()) continue;
			Shark shark = map[i][start].poll();
			map[i][start].clear();
			answer+=shark.z;
			break;
		}
		
//		상어 이동
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j].isEmpty()) continue;
				Shark shark = map[i][j].poll();
				map[i][j].clear();
				
				int y = i, x = j;
				
//				상
				if(shark.dir == 0) {
					int pos = (i + dy[shark.dir]*shark.speed)%((N-1)*2);
//					음수
					if(pos < 0) {
						pos *= -1;
						if(pos < N) {
							shark.dir = 1;
							y = pos;
						}
						else {
							pos = (N-1)*2 - pos;
							y = pos;
						}
					}
//					양수
					else {
						y = pos;
					}
				}
//				하
				else if(shark.dir == 1) {
					int pos = (i + dy[shark.dir]*shark.speed)%((N-1)*2);
					if(pos >= N) {
						shark.dir = 0;
						pos = (N-1)*2 - pos;
					}
					y = pos;
				}
//				우
				else if(shark.dir == 2) {
					int pos = (j + dx[shark.dir]*shark.speed)%((M-1)*2);
					if(pos >= M) {
						shark.dir = 3;
						pos = (M-1)*2 - pos;
					}
					x = pos;
				}
//				좌
				else {
					int pos = (j + dx[shark.dir]*shark.speed)%((M-1)*2);
//					음수
					if(pos < 0) {
						pos *= -1;
						if(pos < M) {
							shark.dir = 2;
							x = pos;
						}
						else {
							pos = (M-1)*2 - pos;
							x = pos;
						}
					}
//					양수
					else {
						x = pos;
					}
				}
				
				temp[y][x].add(shark);
			}
		}
		
		t = temp;
		temp = map;
		map = t;
		
		
	}
	
	static class Shark {
		int speed, dir, z;

		public Shark(int speed, int dir, int z) {
			this.speed = speed;
			this.dir = dir;
			this.z = z;
		}
		
	}
}