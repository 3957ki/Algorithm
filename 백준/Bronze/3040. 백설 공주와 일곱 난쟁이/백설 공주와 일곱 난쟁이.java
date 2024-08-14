import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int[] arr = new int[9];
	static int[] temp_arr = new int[7];
	static int sum = 0;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		comb(0, 0);
	}
	
	static void comb(int cnt, int start) {
		if(cnt == 7) {
			if(sum == 100) {
				for(int num : temp_arr) {
					sb.append(num).append('\n');
				}
				System.out.println(sb);
				System.exit(0);
			}
			return;
		}
		
		for(int i = start; i < 9; i++) {
			sum += arr[i];
			temp_arr[cnt] = arr[i];
			comb(cnt+1, i+1);
			sum -= arr[i];
		}
	}
}