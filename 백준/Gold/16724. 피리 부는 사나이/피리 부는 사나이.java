import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static int N, M, L, answer;
	static int[] parents;
	static int[] dx = {0, 0, -1, 1};	//상하좌우
	static int[] dy = {-1, 1, 0, 0};
	static HashMap<Character, Integer> convert = new HashMap<>();
	static char[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
//		좌표 개수
		L = N*M;
		parents = new int[L];
		make();
		
//		방문배열
		boolean[][] visited = new boolean[N][M];
		
		convert.put('U', 0);
		convert.put('D', 1);
		convert.put('L', 2);
		convert.put('R', 3);
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(visited[i][j]) continue;
				visited[i][j] = true;
				
				func(i, j, visited);
			}
		}
		
		System.out.println(answer);
		
	}
	
	static void func(int i, int j, boolean[][] visited) {
		while(true) {
			int val = i*M + j;
			int dir = convert.get(map[i][j]);
			int y = i + dy[dir];
			int x = j + dx[dir];
//		다음 좌표가 방문하지 않았다면 union
			if(!visited[y][x]) {
				visited[y][x] = true;
				union(val, y*M + x);
			}
//			방문한 곳이라면 union해보고 안되면 answer++;
			else {
				if(!union(val, y*M + x)) answer++;
				return;
			}
			i = y;
			j = x;
		}
	}
	
	static void make() {
		for(int i = 0; i < L; i++) {
			parents[i] = -1;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] < 0) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
		return true;
	}

}