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
			String[] str = s.split("\\\\");
			int L = str.length;
			trie.insert(str, L);
		}
		trie.find(trie.root, 0);
		System.out.println(sb);
	}

	static class Trie{
		Node root = new Node(false);
		
		void insert(String[] str, int L) {
			Node temp = root;
			for(int i = 0; i < L; i++) {
//				자식 노드에 있다면
				if(temp.map.containsKey(str[i])) {
					temp = temp.map.get(str[i]);
				}
//				없으면 만들기
				else {
					Node node = new Node(false);
					temp.map.put(str[i], node);
					temp = node;
				}
			}
			temp.isLast = true;
		}
		
		void find(Node node, int cnt) {
			for(String str : node.map.keySet()) {
				for(int i = 0; i < cnt; i++) {
					sb.append(" ");
				}
				sb.append(str).append('\n');
				find(node.map.get(str), cnt+1);
			}
		}
	}
	
	static class Node{
		TreeMap<String, Node> map = new TreeMap<>((o1, o2) -> o1.compareTo(o2));
		boolean isLast;
		public Node(boolean isLast) {
			this.isLast = isLast;
		}
		
	}
}