import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	static HashSet<Character> set;
	static int[] charCnt;
	static int answer = 0;;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		charCnt = new int['z'+1];
		char[] arr = br.readLine().toCharArray();
		set = new HashSet<>();
		for(char c : arr) {
			charCnt[c]++;
			set.add(c);
		}
		
		DFS(0, arr.length, '.');
		
		System.out.println(answer);
	}
	
	static void DFS(int cnt, int N, char prev) {
		if(cnt == N) {
			answer++;
			return;
		}
		
		for(Character c : set) {
			if(charCnt[c] == 0 || c == prev) continue;
			charCnt[c]--;
			DFS(cnt+1, N, c);
			charCnt[c]++;
		}
	}
}