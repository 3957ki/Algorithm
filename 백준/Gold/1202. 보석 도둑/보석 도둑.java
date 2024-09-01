import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Node[] jewel = new Node[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			jewel[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
//		가벼운 보석 순으로, 무게가 같으면 비싼순으로 정렬
		Arrays.sort(jewel, (o1, o2) -> {
			if(o1.w == o2.w) {
				return o2.v - o1.v;
			}
			return o1.w - o2.w;
		});
		
		int[] bag = new int[M];
		for(int i = 0; i < M; i++) {
			bag[i] = Integer.parseInt(br.readLine());
		}
//		작은 가방 순으로 정렬
		Arrays.sort(bag);
		
//		가격비싼순으로 큐에 넣기
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		long answer = 0;
//		작은 가방 순으로 탐색
		int j = 0;
		for(int i = 0; i < M; i++) {
//			현재 가방에 들어 갈 수 있는 보석 모두 넣기
			while(j < N && jewel[j].w <= bag[i]) {
				pq.add(jewel[j++].v);
			}
			
//			넣은 보석들 중에 젤 비싼거 뽑아서 저장
			if(!pq.isEmpty()) {
				answer += pq.poll();
			}
		}
		System.out.println(answer);
	}

	static class Node{
		int w, v;

		public Node(int w, int v) {
			this.w = w;
			this.v = v;
		}
		
	}
}