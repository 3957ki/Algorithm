import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] parents;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		make();
		
		st = new StringTokenizer(br.readLine());
		int trueCnt = Integer.parseInt(st.nextToken());
		if(trueCnt == 0) {
			System.out.println(M);
			return;
		}
		
		int start = Integer.parseInt(st.nextToken());
		for(int i = 1; i < trueCnt; i++) {
			int end = Integer.parseInt(st.nextToken());
			union(start, end);
			start = end;
		}
		
		Queue<Integer> party = new ArrayDeque<>();
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int peopleCnt = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			party.add(s);
			for(int j = 1; j < peopleCnt; j++) {
				int e = Integer.parseInt(st.nextToken());
				union(s, e);
			}
		}
		
		while(!party.isEmpty()) {
			int num = party.poll();
			if(findSet(start) == findSet(num)) M--;
		}
		System.out.println(M);
	}
	
	static void make() {
		for(int i = 1; i <= N; i++) {
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