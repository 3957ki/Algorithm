import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N, ans;
	static boolean[] col, left, right;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		col = new boolean[N+1];
		left = new boolean[2*N];
		right = new boolean[2*N+1];
		
		setQueens(1);
		System.out.println(ans);
	}
	
	static void setQueens(int rowNo) {
		
		if(rowNo > N) {
			ans++;
			return;
		}
		
		for(int c = 1; c <= N; c++) {
			if(col[c] || left[rowNo-c+N] || right[rowNo+c]) continue;
			col[c] = true;
			left[rowNo-c+N] = true;
			right[rowNo+c] = true;
			setQueens(rowNo+1);
			col[c] = false;
			left[rowNo-c+N] = false;
			right[rowNo+c] = false;
		}
	}

}