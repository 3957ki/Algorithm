import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int[] answer;
	static List<Integer>[] edges;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		edges = new List[N+1];
		for(int i = 1; i <= N; i++) {
			edges[i] = new ArrayList<>();
		}

		answer = new int[N+1];

		// 1개 버리기
		st = new StringTokenizer(br.readLine());
		st.nextToken();

		for(int i = 2; i <= N; i++){
			int now = Integer.parseInt(st.nextToken());
			edges[now].add(i);
		}

		while(M-- > 0){
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			answer[i] += w;
		}

		DFS(1);

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++){
			sb.append(answer[i]).append(' ');
		}
		System.out.println(sb);
    }

	static void DFS(int now){
		for(int next : edges[now]){
			answer[next] += answer[now];
			DFS(next);
		}
	}
}