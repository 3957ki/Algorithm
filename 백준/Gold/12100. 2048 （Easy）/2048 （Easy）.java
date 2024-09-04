import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//성능 요약
//메모리: 30,540 KB, 시간: 152 ms, 코드길이: 3,178 Bytes

//같은 크기의 빈 임시배열을 활용했다.
//0은 제외하고, 임시배열의 현재위치가 비었다면 값을 추가했다.
//값이 비어있지 않다면 같은지 비교하고, 같다면 해당 값을 2배로 증가하고 인덱스를 증가했다.
//같지 않다면 인덱스를 증가시킨 후에 값을 넣었다.
//마지막에 완성된 임시배열을 출력한다.

public class Main {

	static int N;
	static int[][] answer;
	static int[][] map;
	static int max = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		answer = new int[N][N];
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max = Math.max(max, map[i][j]);
			}
		}
		
		DFS(0);
		System.out.println(max);
	}
	
	static void DFS(int cnt) {
		if(cnt == 5) return;
//		복사
		int[][] temp = new int[N][N];
		for(int i = 0; i < N; i++) {
			temp[i] = Arrays.copyOf(map[i], N);
		}
		
		
//		위
		for(int j = 0; j < N; j++) {
			int index = 0;
			for(int i = 0; i < N; i++) {
				//						0은 패스
				if(map[i][j] == 0) continue;
				//						빈 자리라면 넣기
				if(answer[index][j] == 0) {
					answer[index][j] = map[i][j];
				}
				//						아니라면
				else {
					//							값이 같다면 2배로 넣고 이동
					if(answer[index][j] == map[i][j]) {
						answer[index++][j] *= 2;
						max = Math.max(max, map[i][j]*2);
					}
					//							값이 다르다면 이동해서 넣기
					else {
						answer[++index][j] = map[i][j];
					}
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			map[i] = Arrays.copyOf(answer[i], N);
			Arrays.fill(answer[i], 0);
		}
		
		DFS(cnt+1);
		for(int i = 0; i < N; i++) {
			map[i] = Arrays.copyOf(temp[i], N);
		}
//		아래
		for(int j = 0; j < N; j++) {
			int index = N-1;
			for(int i = N-1; i >= 0; i--) {
				//						0은 패스
				if(map[i][j] == 0) continue;
				//						빈 자리라면 넣기
				if(answer[index][j] == 0) {
					answer[index][j] = map[i][j];
				}
				//						아니라면
				else {
					//							값이 같다면 2배로 넣고 이동
					if(answer[index][j] == map[i][j]) {
						answer[index--][j] *= 2;
						max = Math.max(max, map[i][j]*2);
					}
					//							값이 다르다면 이동해서 넣기
					else {
						answer[--index][j] = map[i][j];
					}
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			map[i] = Arrays.copyOf(answer[i], N);
			Arrays.fill(answer[i], 0);
		}
		
		DFS(cnt+1);
		for(int i = 0; i < N; i++) {
			map[i] = Arrays.copyOf(temp[i], N);
		}
//		왼쪽
		for(int i = 0; i < N; i++) {
			int index = 0;
			for(int j = 0; j < N; j++) {
				//						0은 패스
				if(map[i][j] == 0) continue;
				//						빈 자리라면 넣기
				if(answer[i][index] == 0) {
					answer[i][index] = map[i][j];
				}
				//						아니라면
				else {
					//							값이 같다면 2배로 넣고 이동
					if(answer[i][index] == map[i][j]) {
						answer[i][index++] *= 2;
						max = Math.max(max, map[i][j]*2);
					}
					//							값이 다르다면 이동해서 넣기
					else {
						answer[i][++index] = map[i][j];
					}
				}
			}
		}
		
		for(int i = 0; i < N; i++) {
			map[i] = Arrays.copyOf(answer[i], N);
			Arrays.fill(answer[i], 0);
		}
		
		DFS(cnt+1);
		for(int i = 0; i < N; i++) {
			map[i] = Arrays.copyOf(temp[i], N);
		}
//		오른쪽
		for(int i = 0; i < N; i++) {
			int index = N-1;
			for(int j = N-1; j >= 0; j--) {
				//						0은 패스
				if(map[i][j] == 0) continue;
				//						빈 자리라면 넣기
				if(answer[i][index] == 0) {
					answer[i][index] = map[i][j];
				}
				//						아니라면
				else {
					//							값이 같다면 2배로 넣고 이동
					if(answer[i][index] == map[i][j]) {
						answer[i][index--] *= 2;
						max = Math.max(max, map[i][j]*2);
					}
					//							값이 다르다면 이동해서 넣기
					else {
						answer[i][--index] = map[i][j];
					}
				}
			}
		}
		for(int i = 0; i < N; i++) {
			map[i] = Arrays.copyOf(answer[i], N);
			Arrays.fill(answer[i], 0);
		}
		
		DFS(cnt+1);
	}

}