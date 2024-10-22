import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Node[] cnt = new Node[M];
		for(int i = 0; i < M; i++) cnt[i] = new Node(i);
		
		int[][] arr = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				cnt[j].cnt+=arr[i][j];
			}
		}
		
//		들은 사람이 적은순으로 정렬
		Arrays.sort(cnt, (o1, o2) -> o1.cnt-o2.cnt);
		boolean[] hear = new boolean[N];
		
		for(int i = 0; i < M; i++) {
			int index = cnt[i].index;
			for(int j = 0; j < N; j++) {
//				못들었을 때
				if(arr[j][index] == 0) {
//					이미 이전에 들은 적이 있다면 불가능
					if(hear[j]) {
						System.out.println("NO");
						return;
					}
				}
//				들었을 때 표시
				else {
					hear[j] = true;
				}
			}
		}
		System.out.println("YES");
	}

	static class Node{
		int cnt, index;

		public Node(int index) {
			this.index = index;
		}
		
	}
}