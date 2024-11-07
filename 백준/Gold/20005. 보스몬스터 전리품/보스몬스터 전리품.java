import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		char[][] map = new char[N][M];
		List<Node> human = new ArrayList<>();
		Node boss;
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			for(int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if(map[i][j] == 'X' || map[i][j] == '.') continue;
//				보스 위치
				if(map[i][j] == 'B') boss = new Node(i, j, 'B');
//				사람 위치
				else human.add(new Node(i, j, map[i][j]));
			}
		}
		
//		데미지
		int[] damage = new int['z'+1];
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(br.readLine());
			char player = st.nextToken().charAt(0);
			damage[player] = Integer.parseInt(st.nextToken());
		}
		
//		보스 체력
		int hp = Integer.parseInt(br.readLine());
		
		int[] dx = {0, 0, 1, -1};
		int[] dy = {1, -1, 0, 0};
		
//		공격 순서대로 언제 몇명이 얼마의 데미지를 입히는지
		TreeMap<Integer, Attack> attack = new TreeMap<>((o1, o2) -> o1 - o2);
		
		A: for(Node player : human) {
//			bfs
			Queue<Node> queue = new ArrayDeque<>();
			boolean[][] visited = new boolean[N][M];
			queue.add(player);
			visited[player.y][player.x] = true;
			
			int dst = 0;
			while(!queue.isEmpty()) {
				dst++;
				int L = queue.size();
				
				while(L-- > 0) {
					Node now = queue.poll();
					
					for(int d = 0; d < 4; d++) {
						int y = now.y+dy[d];
						int x = now.x+dx[d];
						if(y < 0 || y >= N || x < 0 || x >= M || visited[y][x] || map[y][x] == 'X') continue;
						visited[y][x] = true;
//						보스를 만나면 종료
						if(map[y][x] == 'B') {
//							같은 시간에 도착한 사람이 있다면
							if(attack.containsKey(dst)) {
								attack.put(dst, new Attack(attack.get(dst).cnt+1, attack.get(dst).dmg+damage[now.who]));
							}
							else {
								attack.put(dst, new Attack(1, damage[now.who]));
							}
							continue A;
						}
						queue.add(new Node(y, x, now.who));
					}
				}
			}
		}
		
		int dmg = 0;
		int time = 0;
		int answer = 0;
		for(Integer dst : attack.keySet()) {
//			공격
			hp -= (dst-time)*dmg;
//			죽었다면 break
			if(hp <= 0) break;
//			데미지와 인원수 증가
			dmg += attack.get(dst).dmg;
			answer += attack.get(dst).cnt;
			time = dst;
		}
		
		System.out.println(answer);
		
	}

	static class Attack{
		int cnt, dmg;

		public Attack(int cnt, int dmg) {
			super();
			this.cnt = cnt;
			this.dmg = dmg;
		}

		@Override
		public String toString() {
			return "Attack [cnt=" + cnt + ", dmg=" + dmg + "]";
		}
		
	}
	
	static class Node{
		int y, x;
		char who;

		public Node(int y, int x, char who) {
			super();
			this.y = y;
			this.x = x;
			this.who = who;
		}
		
	}
}