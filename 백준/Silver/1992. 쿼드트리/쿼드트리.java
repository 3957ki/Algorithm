import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N, arr[][], zero, one;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			String st = br.readLine();
			for(int j = 0; j < N; j++) {
				arr[i][j] = st.charAt(j) - '0';
			}
		}
		cut(0, 0, N);
		
		System.out.println(sb);
		
	}

	static void cut(int y, int x, int size) {
		int sum = 0;
		for(int i = y, iEnd = y+size; i < iEnd; i++) {
			for(int j = x, jEnd = x+size; j < jEnd; j++) {
				sum+=arr[i][j];
			}
		}
		
		if(sum==0) {
			sb.append(0);
		}
		else if(sum == size*size) {
			sb.append(1);
		}
		else {
			int half = size/2;
			sb.append('(');
			cut(y, x, half);
			cut(y, x+half, half);
			cut(y+half, x, half);
			cut(y+half, x+half, half);
			sb.append(')');
		}
	}
}