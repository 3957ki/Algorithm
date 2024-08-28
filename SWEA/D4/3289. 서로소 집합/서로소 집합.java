import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int[] parents;
	static int N;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append('#').append(t).append(' ');
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			parents = new int[N+1];
			make();
			
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int cmd = Integer.parseInt(st.nextToken());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				if(cmd == 0) {
					union(start, end);
				}
				else {
					if(findSet(start) == findSet(end)) {
						sb.append(1);
					}
					else {
						sb.append(0);
					}
				}
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	static void make() {
		for(int i = 1; i <= N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] == a) {
			return a;
		}
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}
}