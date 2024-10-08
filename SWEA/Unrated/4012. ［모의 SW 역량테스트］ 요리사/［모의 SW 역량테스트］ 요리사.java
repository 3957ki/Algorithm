import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//성능 요약
//메모리: 21,636 KB, 시간: 253 ms, 코드길이: 1,469 Bytes

//조합으로 N/2개 뽑아서 분리
//True면 A요리 시너지 구하기
//False면 B요리 시너지 구하기

public class Solution {
	static int N, select;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= T; t++) {
			int answer = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			select = (1<<(N/2))-1;
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			do {
				long sumA = 0;
				long sumB = 0;
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(((select>>i)&1) == 1 && ((select>>j)&1) == 1) {
							sumA += arr[i][j];
						}
						
						else if(((select>>i)&1) == 0 && ((select>>j)&1) == 0) {
							sumB += arr[i][j];
						}
					}
				}
				answer = Math.min((int)Math.abs(sumA-sumB), answer);
			}while(np());
			
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}
	
	static boolean np() {
		int i = 0;
		while(i < N-1 && ((select>>(i+1))&1) >= ((select>>i)&1)) i++;
		
		if(i == N-1) return false;
		
		int j = 0;
		while(((select>>(i+1))&1) >= ((select>>j)&1)) j++;
		
		select^=1<<(i+1);
		select^=1<<j;
		
		int k = 0;
		while(i > k && (((select>>i)&1)) != ((select>>k)&1)) {
			select^=1<<i;
			select^=1<<k;
			i--;
			k++;
		}
		if(select>>(N-1) == 1) return false;
		return true;
	}
	
}
