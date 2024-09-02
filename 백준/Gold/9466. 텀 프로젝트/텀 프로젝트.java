import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] arr;
	static boolean[] visited;
	static int answer = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
//			방문 배열
			visited = new boolean[N+1];
//			
			arr = new int[N+1];
//			경로 리스트
			List<Integer> temp = new ArrayList<>();
			
			for(int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = 0;
			for(int i = 1; i <= N; i++) {
//				방문한건 패스
				if(visited[i]) continue;
				visited[i] = true;
				temp.add(i);
				DFS(i, temp);
				temp.clear();
			}
			sb.append(answer).append('\n');
		}
		System.out.println(sb);
	}

	static void DFS(int start, List<Integer> temp) {
		
		int next = arr[start];
//		방문했다면
		if(visited[next]) {
			if(temp.contains(next)) {
				int L = temp.indexOf(next);
				answer += L;
			}
			else {
				answer+=temp.size();
			}
			return;
		}
		
		visited[next] = true;
		temp.add(next);
		DFS(next, temp);
		
	}
	
}