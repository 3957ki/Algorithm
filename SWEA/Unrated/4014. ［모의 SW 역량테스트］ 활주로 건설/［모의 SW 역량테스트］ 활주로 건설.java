import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			
			int[][] map = new int[N][N];
			
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int answer = 0;
			
			A: for(int i = 0; i < N; i++) {
				int curH = map[i][0];
				int curCnt = 1;
				boolean flag = false;
				for(int j = 1; j < N; j++) {
//					차이가 1보다 크면 패스
					if(Math.abs(curH-map[i][j]) > 1) continue A;
//					flag가 false이고 낮아질 때
					if(map[i][j] < curH && !flag) {
						flag = true;
						curH = map[i][j];
						curCnt = 1;
						continue;
					}
					
//					flag가 true이고 낮아질 때
					if(map[i][j] < curH && flag) {
						if(curCnt < X) continue A;
						curH = map[i][j];
						curCnt = 1;
						continue;
					}
						
//					flag가 false이고 높아질 때
					if(map[i][j] > curH && !flag) {
						if(curCnt < X) continue A;
						curH = map[i][j];
						curCnt = 1;
						continue;
					}
					
//					flag가 true고 높아질 때
					if(map[i][j] > curH && flag) {
						flag = false;
						if(curCnt < X*2) continue A;
						curH = map[i][j];
						curCnt = 1;
						continue;
					}
					
//					높이가 같을 때
					if(map[i][j] == curH) curCnt++;
					
				}
//				flag가 false이고 curCnt가 X보다 작을 때
				if(curCnt < X && flag) continue;
				answer++;
			}
			
			A: for(int j = 0; j < N; j++) {
				int curH = map[0][j];
				int curCnt = 1;
				boolean flag = false;
				for(int i = 1; i < N; i++) {
//					차이가 1보다 크면 패스
					if(Math.abs(curH-map[i][j]) > 1) continue A;
					
//					flag가 false이고 낮아질 때
					if(map[i][j] < curH && !flag) {
						flag = true;
						curH = map[i][j];
						curCnt = 1;
						continue;
					}
					
//					flag가 true이고 낮아질 때
					if(map[i][j] < curH && flag) {
						if(curCnt < X) continue A;
						curH = map[i][j];
						curCnt = 1;
						continue;
					}
					
//					flag가 false이고 높아질 때
					if(map[i][j] > curH && !flag) {
						if(curCnt < X) continue A;
						curH = map[i][j];
						curCnt = 1;
						continue;
					}
					
//					flag가 true고 높아질 때
					if(map[i][j] > curH && flag) {
						flag = false;
						if(curCnt < X*2) continue A;
						curH = map[i][j];
						curCnt = 1;
						continue;
					}
					
//					높이가 같을 때
					if(map[i][j] == curH) curCnt++;
					
				}
//				flag가 false이고 curCnt가 X보다 작을 때
				if(curCnt < X && flag) continue;
				answer++;
			}
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

}