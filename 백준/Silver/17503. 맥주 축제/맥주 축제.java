import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		Drink[] arr = new Drink[K];
		
		for(int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
//			선호도, 도수
			int fav = Integer.parseInt(st.nextToken());
			int degree = Integer.parseInt(st.nextToken());
			arr[i] = new Drink(fav, degree);
		}
		Arrays.sort(arr, (o1, o2) -> o1.degree-o2.degree);
		
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		int sumFav = 0;
		for(int i = 0; i < N; i++) {
//			선호도의 합 저장
			sumFav += arr[i].fav;
//			선호도 저장
			pq.add(arr[i].fav);
			if(sumFav >= M) {
				System.out.println(arr[N-1].degree);
				return;
			}
		}
		for(int i = N; i < K; i++) {
			pq.add(arr[i].fav);
			sumFav = sumFav + arr[i].fav - pq.poll();
			if(sumFav >= M) {
				System.out.println(arr[i].degree);
				return;
			}
		}
		System.out.println(-1);
	}
	
	static class Drink{
		int fav, degree;

		public Drink(int fav, int degree) {
			this.fav = fav;
			this.degree = degree;
		}
		
	}

}