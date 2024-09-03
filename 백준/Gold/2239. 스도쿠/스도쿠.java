import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static int[] row = new int[9];
	static int[] col = new int[9];
	static int[][] section = new int[3][3];
	static int[][] map = new int[9][9];
	static List<Integer> blankList = new ArrayList<>();
	static int L;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 9; i++){
			String str = br.readLine();
			for(int j = 0; j < 9; j++){
				map[i][j] = str.charAt(j) - '0';
				if(map[i][j] == 0) {
					blankList.add(i*9+j);
				}
//				0이 아니면 해당 영역 비트에 or연산
				else {
					row[i] |= 1<<map[i][j];
					col[j] |= 1<<map[i][j];
					section[i/3][j/3] |= 1<<map[i][j];
				}
			}
		}
		
		L = blankList.size();
		
		DFS(0);
		
	}
	
	static void DFS(int start) {
//		끝까지 도달했다면 map 출력하고 종료
		if(start == L) {
			for(int[] a : map) {
				for(int n : a) {
					sb.append(n);
				}
				sb.append('\n');
			}
			System.out.println(sb);
			System.exit(0);
		}
//		현재 빈 좌표 값
		int now = blankList.get(start);
		int y = now/9;
		int x = now%9;
		
//		넣을 수 있는 값 확인해보기
		for(int i = 1; i <= 9; i++) {
//			해당 행, 열, 구역 모두에서 i값이 없을 때 재귀
			if((row[y] & 1<<i) == 0 && (col[x] & 1<<i) == 0 && (section[y/3][x/3] & 1<<i) == 0){
				map[y][x] = i;
				row[y] ^= 1<<i;
				col[x] ^= 1<<i;
				section[y/3][x/3] ^= 1<<i;
				DFS(start+1);
				row[y] ^= 1<<i;
				col[x] ^= 1<<i;
				section[y/3][x/3] ^= 1<<i;
			}
		}
	}

}