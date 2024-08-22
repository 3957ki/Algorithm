import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Queue<fireball>[][] map = new LinkedList[N+1][N+1];
		Queue<fireball>[][] temp = new LinkedList[N+1][N+1];
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				map[i][j] = new LinkedList<>();
				temp[i][j] = new LinkedList<>();
			}
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			map[r][c].add(new fireball(m, s, d));
		}

		int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
		int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

//		K번 이동시키기
		for(int k = 0; k < K; k++) {
			//이동시키기
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					//비었다면 넘어가기
					if(map[i][j].isEmpty()) continue;
					
					//큐에 있는 파이어볼 모두 이동시키기
					while(!map[i][j].isEmpty()) {
						fireball fb = map[i][j].poll();
						int speed = fb.s;
						int dir = fb.d;

						int y = i+dy[dir]*speed;
						int x = j+dx[dir]*speed;

						if(y < 1) {
							y = N + y%N;
						}
						if(y > N ) {
							y = (y-1)%N+1;
						}
						if(x < 1) {
							x = N + x%N;
						}
						if(x > N) {
							x = (x-1)%N+1;
						}
						//temp에 이동한 위치에 넣기
						temp[y][x].add(fb);
					}
				}
			}
			Queue<fireball>[][] t = map;
			map = temp;
			temp = t;
			
//			이동이 끝난 뒤
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
//					비어있으면 넘어가기
					if(map[i][j].isEmpty()) continue;
//					파이어볼이 하나면 그대로 복사하기
					if(map[i][j].size() == 1) {
						temp[i][j].add(map[i][j].poll());
						continue;
					}
					//fireball 개수가 2이상
					if(map[i][j].size() >= 2) {
						int mSum = 0;
						int sSum = 0;
						int size = map[i][j].size();
						int dSum = 0;
						while(!map[i][j].isEmpty()) {
							fireball fb = map[i][j].poll();
							mSum += fb.m;
							sSum += fb.s;
							dSum += fb.d%2;
						}
//						각 질량과 속력
						int m = mSum/5;
						int s = sSum/size;
						
						if(m == 0) continue;
						
//						방향 정하기
						int num = 1;
//						0, 2, 4, 6
						if(dSum == 0 || dSum == size) num = 0;
						
						for(int d = 0+num; d < 8; d+=2) {
							//temp에 이동한 위치에 넣기
							temp[i][j].add(new fireball(m, s, d));
						}
					}
					
				}
			}
			t = map;
			map = temp;
			temp = t;
		}
		
		
		
//		남은 파이어볼 질량 합 계산
		int answer = 0;
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(map[i][j].isEmpty()) continue;
				while(!map[i][j].isEmpty()) {
					answer += map[i][j].poll().m;
				}
			}
		}
		System.out.println(answer);

	}

	static class fireball {
		int m, s, d;

		public fireball(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}

	}
}