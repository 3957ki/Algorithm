import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[] visited;
	static ArrayDeque<Integer> deque = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        DFS(0);
        System.out.println(sb);
    }
    
    static void DFS(int depth) {
    	if(depth == M) {
    		for(Integer num : deque) {
    			sb.append(num).append(' ');
    		}
    		sb.append('\n');
    		return;
    	}
    	for(int i = 1; i <= N; i++) {
    		if(!visited[i]) {
    			visited[i] = true;
    			deque.addLast(i);
    			DFS(depth+1);
    			deque.removeLast();
    			visited[i] = false;
    		}
    	}
    }
}