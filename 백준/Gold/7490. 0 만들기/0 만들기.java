import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static char[] select = {' ', '+', '-'};
	static char[] temp;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
//			임시 연산자 배열
			temp = new char[N-1];
			perm(0);
			sb.append('\n');
		}
		System.out.println(sb);
	}

	static void perm(int cnt) {
		if(cnt == N-1) {
			calc();
			return;
		}
		
		for(int i = 0; i < 3; i++) {
			temp[cnt] = select[i];
			perm(cnt+1);
		}
	}
	
	static void calc() {
		String s = ""+1;
		for(int i = 1; i < N; i++) {
			if(temp[i-1] != ' ') {
				s+=" "+temp[i-1]+" ";
			}
			s+=i+1;
		}
		
		String[] list = s.split(" ");
		
		int now = Integer.parseInt(list[0]);
		for(int i = 1; i < list.length; i+=2) {
			if(list[i].equals("+")) {
				now+=Integer.parseInt(list[i+1]);
			}
			else if(list[i].equals("-")) {
				now-=Integer.parseInt(list[i+1]);
			}
		}
		
		if(now == 0) {
			sb.append(1);
			for(int i = 1; i < N; i++) {
				sb.append(temp[i-1]).append(i+1);
			}
			sb.append('\n');
		}
	}
}