import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Solution {
	
	static TreeMap<Integer, String> map;
	static Queue<Character> queue;
	static int N, L;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
//			한 변의 길이
			L = N/4;
			
			queue = new ArrayDeque<>();
//			오름차순 정렬
			map = new TreeMap<>((o1, o2) -> {
				return o2 - o1;
			});
			
//			큐에 숫자들 넣기
			String str = br.readLine();
			for(int i = 0; i < N; i++) {
				queue.add(str.charAt(i));
			}
			
			func();
			int answer = 0;
			
			for(Integer num : map.keySet()) {
				if(--K == 0) {
					answer = num;
				}
			}
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

	static void func() {
		
//		한 변의 길이만큼 반복
		for(int i = 0; i < L; i++) {
//			숫자 4개 뽑기
			for(int j = 0; j < 4; j++) {
//				숫자 1개만들기
				String num = "0x";
				for(int k = 0; k < L; k++) {
					char c = queue.poll();
					num+=c;
					queue.add(c);
				}
//				10진수로 변환
				Integer value = Integer.decode(num);
//				중복하지 않으면
				if(!map.containsKey(value)) {
					map.put(value, num);
				}
			}
			
//			1칸 밀기
			char temp = queue.poll();
			queue.add(temp);
		}
	}
}