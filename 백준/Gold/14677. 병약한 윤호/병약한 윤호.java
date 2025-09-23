import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Main {
	static Map<Character, Character> map = new HashMap<>();

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()) * 3;
		char[] arr = br.readLine().toCharArray();

		Queue<Node> queue = new ArrayDeque<>();
		Set<Integer> visited = new HashSet<>();
		if(arr[0] == 'B') {
			visited.add(10000 + N-1);
			queue.add(new Node('L', 1, N-1));
		}
		if(arr[N-1] == 'B') {
			visited.add(N-2);
			queue.add(new Node('L', 0, N-2));
		}

		map.put('B', 'L');
		map.put('L', 'D');
		map.put('D', 'B');


		int dst = 0;
		A: while(!queue.isEmpty()){
			dst++;
			int L = queue.size();
			while(L-- > 0){
				Node now = queue.poll();

				if(now.start > now.end) break A;

				if(arr[now.start] == now.ch && !visited.contains((now.start + 1)*10000 + now.end)){
					visited.add((now.start + 1)*10000 + now.end);
					queue.add(new Node(map.get(now.ch), now.start + 1, now.end));
				}

				if(arr[now.end] == now.ch && !visited.contains(now.start*10000 + now.end - 1)){
					visited.add(now.start*10000 + now.end - 1);
					queue.add(new Node(map.get(now.ch), now.start, now.end - 1));
				}
			}
		}

		System.out.println(dst);
    }

	static class Node{
		char ch;
		int start, end;

		Node(char ch, int start, int end){
			this.ch = ch;
			this.start = start;
			this.end = end;
		}
	}
}