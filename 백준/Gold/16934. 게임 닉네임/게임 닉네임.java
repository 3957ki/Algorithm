import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Trie trie = new Trie();
		
		for(int i = 0; i < N; i++) {
			String s = br.readLine();
			char[] str = s.toCharArray();
			int L = str.length;
			trie.insert(str, L);
		}
		System.out.println(sb);
	}

	static class Trie{
		Node root = new Node();
		
		void insert(char[] str, int L) {
			Node temp = root;
			boolean flag = false;
			for(int i = 0; i < L; i++) {
//				자식 노드에 있다면
				if(temp.map.containsKey(str[i])) {
					sb.append(str[i]);
					temp = temp.map.get(str[i]);
				}
//				없으면 만들기
				else {
					if(!flag) {
						flag = true;
						sb.append(str[i]);
					}
					Node node = new Node();
					temp.map.put(str[i], node);
					temp = node;
				}
			}
			if(!flag && temp.isLast) sb.append(++temp.num);
			temp.isLast = true;
			sb.append('\n');
		}
		
	}
	
	static class Node{
		TreeMap<Character, Node> map = new TreeMap<>((o1, o2) -> o1-o2);
		boolean isLast = false;
		int num = 1;
		
	}
}