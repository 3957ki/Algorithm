import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N, M;
	static int[] temp, arr;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	static int[] numCnt = new int[10001];
	static Set<Integer> set = new TreeSet<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		temp = new int[M];
		visited = new boolean[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			numCnt[arr[i]]++;
			set.add(arr[i]);
		}
		
		perm(0);
		System.out.println(sb);
	}
	
	static void perm(int cnt) {
		if(cnt == M) {
			for(int num : temp) {
				sb.append(num).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int num : set) {
			if(numCnt[num] == 0) continue;
			numCnt[num]--;
			temp[cnt] = num;
			perm(cnt+1);
			numCnt[num]++;
		}
	}
}