import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int[] parents, arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());

		arr = new int[N+1];
		parents = new int[N+1];
		Arrays.fill(parents, -1);

		st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++){
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num == 0 ? -1 : 1;
		}

		while(M-- > 0){
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			union(start, end);
		}

		while(Q-- > 0){
			int now = Integer.parseInt(br.readLine());
			if(arr[find(now)] > 0) sb.append(1).append('\n');
			else sb.append(0).append('\n');
		}
		System.out.println(sb);
    }

	static int find(int a){
		if(parents[a] < 0) return a;
		return parents[a] = find(parents[a]);
	}

	static void union(int a, int b){
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot == bRoot) return;
		arr[aRoot] += arr[bRoot];
		parents[aRoot] += parents[bRoot];
		parents[bRoot] = aRoot;
	}
}