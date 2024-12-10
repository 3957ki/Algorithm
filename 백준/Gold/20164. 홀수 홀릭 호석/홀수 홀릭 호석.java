import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String N = br.readLine();
		
		calc(N, 0);
		System.out.println(min + " "+ max);
	}
	
	static void calc(String str, int cnt) {
		int L = str.length();
		
//		홀수의 개수 세기
		for(int i = 0; i < L; i++) {
			int num = str.charAt(i) - '0';
			if((num&1) == 1) cnt++;
		}
			
//		길이가 1
		if(L == 1) {
			max = Math.max(max, cnt);
			min = Math.min(min, cnt);
			return;
		}
		
//		길이가 2
		if(L == 2) {
			int newNum = str.charAt(0)-'0' + str.charAt(1)-'0';
			calc(Integer.toString(newNum), cnt);
			return;
		}
		
//		길이가 3
		else {
			for(int i = 0; i < L-2; i++) {
				for(int j = i+1; j < L-1; j++) {
					int newNum = 0;
					int num1 = Integer.parseInt(str.substring(0, i+1));
					int num2 = Integer.parseInt(str.substring(i+1, j+1));
					int num3 = Integer.parseInt(str.substring(j+1, L));
					newNum = num1+num2+num3;
					calc(Integer.toString(newNum), cnt);
				}
			}
		}
	}

}