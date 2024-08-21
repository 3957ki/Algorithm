import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int answer = Integer.MAX_VALUE;
	static int[][] temp;
	static int D, W, K;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			answer = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[D][W];
			temp = new int[D][W];
			for(int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					temp[i][j] = map[i][j];
				}
			}
			
			subset(0, map, 0);
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static void subset(int cnt, int[][] map, int d) {
		if(d == D) {
			if(test(map)) {
				answer = Math.min(answer, cnt);
			};
			return;
		}
		
		subset(cnt, map, d+1);
		
		for(int i = 0; i < W; i++) {
			map[d][i] = 0;
		}
		subset(cnt+1, map, d+1);
		
		for(int i = 0; i < W; i++) {
			map[d][i] = 1;
		}
		subset(cnt+1, map, d+1);
		
		for(int j = 0; j < W; j++) {
			map[d][j] = temp[d][j];
		}
	}
	
	static boolean test(int[][] map) {
		A: for(int j = 0; j < W; j++) {
			int now = map[0][j];
			int c = 1;
			if(c == K) continue;
			for(int i = 1; i < D; i++) {
				if(map[i][j] == now) {
					c++;
					if(c == K) continue A;
				}
				else {
					c = 1;
					now = map[i][j];
				}
			}
			return false;
		}
		return true;
	}

}
