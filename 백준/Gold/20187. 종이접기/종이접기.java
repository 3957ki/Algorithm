import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Queue<Character> cmd = new ArrayDeque<>();
		while(st.hasMoreTokens()) {
			cmd.add(st.nextToken().charAt(0));
		}
		
		int[][] paper = new int[2][2];
		paper[0][0] = 0;
		paper[0][1] = 1;
		paper[1][0] = 2;
		paper[1][1] = 3;
		
		int temp;
		
		char cur = 'U';
		char cur2 = 'L';
		
		while(!cmd.isEmpty()) {
			Character c = cmd.poll();
			
			switch (c) {
			case 'U':
				if(cur != 'U') {
					temp = paper[0][0];
					paper[0][0] = paper[1][0];
					paper[1][0] = temp;
					
					temp = paper[0][1];
					paper[0][1] = paper[1][1];
					paper[1][1] = temp;
					cur = 'U';
				}
				break;
			case 'D':
				if(cur != 'D') {
					temp = paper[0][0];
					paper[0][0] = paper[1][0];
					paper[1][0] = temp;
					
					temp = paper[0][1];
					paper[0][1] = paper[1][1];
					paper[1][1] = temp;
					cur = 'D';
				}
				break;
			case 'R':
				if(cur2 != 'R') {
					temp = paper[0][0];
					paper[0][0] = paper[0][1];
					paper[0][1] = temp;
					
					temp = paper[1][0];
					paper[1][0] = paper[1][1];
					paper[1][1] = temp;
					cur2 = 'R';
				}
				break;
			case 'L':
				if(cur2 != 'L') {
					temp = paper[0][0];
					paper[0][0] = paper[0][1];
					paper[0][1] = temp;
					
					temp = paper[1][0];
					paper[1][0] = paper[1][1];
					paper[1][1] = temp;
					cur2 = 'L';
				}
				break;
			}
		}
		
		int H = Integer.parseInt(br.readLine());
		int y = H/2;
		int x = H%2;
		int N = (int)Math.pow(2, K);
		
		int num = paper[y][x];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N/2; j++) {
				sb.append(num).append(' ').append(num^1).append(' ');
			}
			sb.append('\n');
			num^=1<<1;
		}
		System.out.println(sb);
	}
}
