import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static int N, M, L;
	static int[] temp;
	static Integer[] setArr;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	static Set<Integer> set = new TreeSet<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		temp = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			set.add(arr[i]);
		}
		setArr = set.toArray(new Integer[0]);
		L = setArr.length;
		perm(0, 0);
		System.out.println(sb);
	}
	
	static void perm(int cnt, int start) {
		if(cnt == M) {
			for(int num : temp) {
				sb.append(num).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for(int i = start; i < L; i++) {
			temp[cnt] = setArr[i];
			perm(cnt+1, i);
		}
	}
}