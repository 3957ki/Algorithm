import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		A: for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			
			Trie trie = new Trie();
			
			String[] arr = new String[N];
			
			for(int i = 0; i < N; i++) {
				arr[i] = br.readLine();
			}
			
			Arrays.sort(arr);
			
			for(String str : arr) {
//				실패시
				if(!trie.insert(str)) {
					sb.append("NO").append('\n');
					continue A;
				}
			}
			sb.append("YES").append('\n');
		}
		System.out.println(sb);
	}

	static class Trie{
		Node root = new Node();
		
		public boolean insert(String str) {
			
			int L = str.length();
			
			Node now = root;
			for(int i = 0; i < L; i++) {
				char c = str.charAt(i);
				
//				이미 있을 때
				if(now.map.containsKey(c)) {
					now = now.map.get(c);
				}
				
//				없을 때
				else {
					Node node = new Node();
					now.map.put(c, node);
					now = node;
				}
				
//				종료한 노드를 만났다면
				if(now.isEnd) return false;
			}
			
//			종료 표시
			now.isEnd = true;
			
//			뒤에 더 이어지는 번호가 있다면
//			if(!now.map.isEmpty()) return false;
			
			return true;
		}
	}
	
	static class Node{
		boolean isEnd = false;
		Map<Character, Node> map = new HashMap<>();
	}
}