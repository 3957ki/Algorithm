import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb = new StringBuilder();
	static int[] M, W;
	static int[][] answer;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		M = new int[3];
		W = new int[3];
		answer = new int[3][3];
		
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			M[i] = Integer.parseInt(st.nextToken());
			W[i] = Integer.parseInt(st.nextToken());
		}
		
		A: for(int i = 0; i <= M[0]; i++) {
			answer[0][1] = i;
			answer[0][2] = M[0]-i;
			answer[1][2] = W[2]-answer[0][2];
			answer[1][0] = M[1]-answer[1][2];
			answer[2][0] = W[0]-answer[1][0];
			answer[2][1] = M[2]-answer[2][0];
			
			for(int m = 0; m < 3; m++) {
				for(int w = 0; w < 3; w++) {
					if(m == w) continue;
					if(answer[m][w] < 0) continue A;
				}
			}
			
			sb.append(1).append('\n');
			for(int m = 0; m < 3; m++) {
				for(int w = 0; w < 3; w++) {
					if(m==w) continue;
					sb.append(answer[m][w]).append(' ');
				}
				sb.append('\n');
			}
			System.out.println(sb);
			return;
		}
		System.out.println(0);
	}

}