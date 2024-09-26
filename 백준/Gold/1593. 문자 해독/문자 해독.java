import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[] Wcnt, Scnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Wcnt = new int['z'+1];
		Scnt = new int['z'+1];
		Queue<Character> queue = new ArrayDeque<>();

		String W = br.readLine();
		char[] S = br.readLine().toCharArray();
		for(int i = 0; i < N; i++) {
			Wcnt[W.charAt(i)]++;
			Scnt[S[i]]++;
			queue.add(S[i]);
		}
		
		int cnt = 0;
		if(isValid()) cnt++;
		
		for(int i = N; i < M; i++) {
			Scnt[queue.poll()]--;
			Scnt[S[i]]++;
			queue.add(S[i]);
			if(isValid()) cnt++;
		}
		
		System.out.println(cnt);
	}
	
	static boolean isValid() {
		for(int i = 'A'; i <= 'z'; i++) {
			if(Wcnt[i] != Scnt[i]) return false;
		}
		return true;
	}

}