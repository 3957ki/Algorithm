import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String str = st.nextToken();
			
			int[][] arr = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] answer = new int[N][N];
			
			if(str.equals("up")) {
				for(int j = 0; j < N; j++) {
					int index = 0;
					for(int i = 0; i < N; i++) {
//						0은 패스
						if(arr[i][j] == 0) continue;
//						빈 자리라면 넣기
						if(answer[index][j] == 0) {
							answer[index][j] = arr[i][j];
						}
//						아니라면
						else {
//							값이 같다면 2배로 넣고 이동
							if(answer[index][j] == arr[i][j]) {
								answer[index++][j] *= 2;
							}
//							값이 다르다면 이동해서 넣기
							else {
								answer[++index][j] = arr[i][j];
							}
						}
					}
				}
			}
			else if(str.equals("down")) {
				for(int j = 0; j < N; j++) {
					int index = N-1;
					for(int i = N-1; i >= 0; i--) {
//						0은 패스
						if(arr[i][j] == 0) continue;
//						빈 자리라면 넣기
						if(answer[index][j] == 0) {
							answer[index][j] = arr[i][j];
						}
//						아니라면
						else {
//							값이 같다면 2배로 넣고 이동
							if(answer[index][j] == arr[i][j]) {
								answer[index--][j] *= 2;
							}
//							값이 다르다면 이동해서 넣기
							else {
								answer[--index][j] = arr[i][j];
							}
						}
					}
				}
			}
			else if(str.equals("left")) {
				for(int i = 0; i < N; i++) {
					int index = 0;
					for(int j = 0; j < N; j++) {
//						0은 패스
						if(arr[i][j] == 0) continue;
//						빈 자리라면 넣기
						if(answer[i][index] == 0) {
							answer[i][index] = arr[i][j];
						}
//						아니라면
						else {
//							값이 같다면 2배로 넣고 이동
							if(answer[i][index] == arr[i][j]) {
								answer[i][index++] *= 2;
							}
//							값이 다르다면 이동해서 넣기
							else {
								answer[i][++index] = arr[i][j];
							}
						}
					}
				}
			}
			else if(str.equals("right")) {
				for(int i = 0; i < N; i++) {
					int index = N-1;
					for(int j = N-1; j >= 0; j--) {
//						0은 패스
						if(arr[i][j] == 0) continue;
//						빈 자리라면 넣기
						if(answer[i][index] == 0) {
							answer[i][index] = arr[i][j];
						}
//						아니라면
						else {
//							값이 같다면 2배로 넣고 이동
							if(answer[i][index] == arr[i][j]) {
								answer[i][index--] *= 2;
							}
//							값이 다르다면 이동해서 넣기
							else {
								answer[i][--index] = arr[i][j];
							}
						}
					}
				}
			}
			sb.append('#').append(t).append('\n');
			for(int[] a : answer) {
				for(int num : a) {
					sb.append(num).append(' ');
				}
				sb.append('\n');
			}
		}
		System.out.println(sb);
	}

}