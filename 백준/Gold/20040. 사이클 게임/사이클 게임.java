import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parents = new int[N];
		make();
		
		int answer = 0;
		for(int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if(!union(start, end)) {
				answer = i;
				break;
			}
		}
		
		System.out.println(answer);
	}
	
	static void make() {
		for(int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(a == parents[a]) return a;
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