import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	static List<Character>[] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		map = new ArrayList[26];
		for(int i = 0; i < 26; i++) {
			map[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < N; i++) {
			String str = br.readLine();
			Character son = str.charAt(0);
			Character parent = str.charAt(str.length()-1);
			map[son-97].add(parent);
		}
		
		int M = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < M; i++) {
			char answer;
			String str = br.readLine();
			Character son = str.charAt(0);
			Character parent = str.charAt(str.length()-1);
			answer = DFS(son, parent) ? 'T' : 'F';
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
		
	}

	static boolean DFS(Character son, Character parent) {
		
		for(Character c : map[son-97]) {
			if(c == parent) return true;
			if(DFS(c, parent)) return true;
		}
		return false;
	}
}