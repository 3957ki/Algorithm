import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N, M, L;
	static int[][] map;
	static List<Node> chicken;
	static List<Node> house;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

//		map, 치킨리스트, 집리스트
		map = new int[N][N];
		chicken = new ArrayList<>();
		house = new ArrayList<>();

		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					chicken.add(new Node(i, j));
				}
				else if(map[i][j] == 1) {
					house.add(new Node(i, j));
				}
			}
		}
//		치킨집 개수
		L = chicken.size();
//		조합
		comb(0, 0);
		System.out.println(answer);
	}

	static void comb(int cnt, int c) {
//		치킨집 개수가 M개 넘으면 return
		if(c > M) return;
		if(cnt == L) {
//			치킨집 개수가 0개여도 return
			if(c == 0) return;
//			치킨거리구하기
			BFS();
			return;
		}
//		폐업x
		comb(cnt+1, c+1);
		map[chicken.get(cnt).y][chicken.get(cnt).x] = 0;
//		폐업o
		comb(cnt+1, c);
		map[chicken.get(cnt).y][chicken.get(cnt).x] = 2;
	}

	static void BFS() {
		int sum = 0;
//		집마다 치킨거리 구해서 누적
		for(Node h : house) {
			int dst = Integer.MAX_VALUE;
			int y = h.y;
			int x = h.x;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == 2) {
						dst = Math.min(dst, Math.abs(y-i) + Math.abs(x-j));
					}
				}
			}
			sum+=dst;
		}
//		최소값 갱신
		answer = Math.min(answer, sum);
	}

	static class Node{
		int y, x;

		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}

	}
}