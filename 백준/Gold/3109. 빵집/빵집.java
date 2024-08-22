import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] dy = {-1, 0, 1};
	static int R, C, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		for(int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		answer = 0;
		for(int i = 0; i < R; i++) {
			map[i][0] = 'x';
			connect(i, 0, map);
		}
		System.out.println(answer);
	}

	static boolean connect(int Rcnt, int Ccnt, char[][] map) {
		if(Ccnt == C-1) {
			answer++;
			return true;
		}
		
		for(int d = 0; d < 3; d++) {
			int x = Ccnt+1;
			int y = Rcnt+dy[d];
			if(y < 0 || y >= R) continue;
			if(map[y][x] == 'x') continue;
			map[y][x] = 'x';	
			if(connect(y, Ccnt+1, map)) {
				return true;
			}
		}
		return false;
	}
}