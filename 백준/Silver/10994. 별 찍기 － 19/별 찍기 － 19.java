import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static char[][] map;

	static int N, L;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		L = 4*N-3;
		map = new char[L][L];

		func(0);
		
		for(int i = 0; i < L; i++) {
			for(int j = 0; j < L; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	static void func(int num) {
		if(num == 2*N-1) return;
		char c;
		if((num&1) == 0) c = '*';
		else c = ' ';

		for(int i = num; i < L-num; i++) {
			map[i][num] = c;
			map[num][i] = c;
			map[i][L-num-1] = c;
			map[L-num-1][i] = c;
		}
		
		func(num+1);
	}
}