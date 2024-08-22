import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, numList[];
	static int min, max;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;
			max = Integer.MIN_VALUE;
			N = Integer.parseInt(br.readLine());
			numList = new int[N];
			int[] operatorCnt = new int[4];
			int[] operator = new int[N-1];
//			연산자별 개수 저장 + - * /
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < 4; i++) {
				operatorCnt[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++) {
				numList[i] = Integer.parseInt(st.nextToken());
			}
			
			perm(0, operatorCnt, operator);
			int answer = max-min;
			sb.append('#').append(t).append(' ').append(answer).append('\n');
		}
		System.out.println(sb);
	}

	static void perm(int cnt, int[] operatorCnt, int[] operator) {
		if(cnt == N-1) {
			calc(operator);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
//			사용할 수 없다면
			if(operatorCnt[i] == 0) continue;
//			사용할 수 있다면
			operator[cnt] = i;
			operatorCnt[i]--;
			perm(cnt+1, operatorCnt, operator);
			operatorCnt[i]++;
		}
	}
	
	static void calc(int[] operator) {
		int num = numList[0];
		int result = num;
		for(int i = 1; i < N; i++) {
			switch (operator[i-1]) {
			case 0:
				result+=numList[i];
				break;
			case 1:
				result-=numList[i];
				break;
			case 2:
				result*=numList[i];
				break;
			case 3:
				result/=numList[i];
				break;
			}
		}
		min = Math.min(min, result);
		max = Math.max(max, result);
	}
}