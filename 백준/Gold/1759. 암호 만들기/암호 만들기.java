import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static char[] arr;
	static char[] temp;
	static int L, C;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		temp = new char[L];
		arr = new char[C];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < C; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		
		comb(0, 0, 0, 0);
		System.out.println(sb);
	}
	
	static void comb(int cnt, int start, int aCnt, int bCnt) {
		if(cnt == L) {
			if(aCnt >= 1 && bCnt >= 2) {
				for(char c : temp) {
					sb.append(c);
				}
				sb.append('\n');
			}
			return;
		}
		
		for(int i = start; i < C; i++) {
			temp[cnt] = arr[i];
			if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
				comb(cnt+1, i+1, aCnt+1, bCnt);
			}
			else {
				comb(cnt+1, i+1, aCnt, bCnt+1);
			}
		}
	}

}