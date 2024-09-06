import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long answer = 0;
		//주문
		String order = br.readLine();
		int orderL = order.length();
		
//		문자 확인 배열
		char[] check = new char[orderL+1];
		check[0] = '*';
		for(int i = 1; i <= orderL; i++) {
			check[i] = order.charAt(i-1);
		}
		
//		문자 개수 배열
		long[] cnt = new long[orderL+1];
		cnt[0] = 1;
		
//		다리 입력받기
		String s = br.readLine();
		int L = s.length();
		char[] arr1 = new char[L+1];
		char[] arr2 = new char[L+1];
		arr1[0] = '*';
		arr2[0] = '*';
		
		for(int i = 1; i <= L; i++) {
			arr1[i] = s.charAt(i-1);
		}
		
		s = br.readLine();
		for(int i = 1; i <= L; i++) {
			arr2[i] = s.charAt(i-1);
		}
//		다리 배열
		char[][] arr = {arr1, arr2};
		
//		위부터 시작
		long[][][] dp1 = new long[L+1][2][orderL+1];
//		아래부터 시작
		long[][][] dp2 = new long[L+1][2][orderL+1];

//		위를 처음으로
		for(int i = 1; i <= L; i++) {
//			위에 문자부터 보기
			for(int j = 1; j <= orderL; j+=2) {
//				문자가 다르면 패스
				if(arr1[i] != check[j]) continue;
//				문자가 같으면 
				dp1[i][0][j] = cnt[j]+cnt[j-1];
			}
			
//			아래 문자 보기
			for(int j = 2; j <= orderL; j+=2) {
//				문자가 다르면 패스
				if(arr2[i] != check[j]) continue;
//				문자가 같으면 
				dp1[i][1][j] = cnt[j]+cnt[j-1];
			}
//			문자별 개수 값 갱신
			for(int j = 1; j <= orderL; j++) {
				cnt[j] = Math.max(cnt[j], Math.max(dp1[i][0][j], dp1[i][1][j]));
			}
		}
//		값 저장
		answer+=cnt[orderL];
		
//		초기화
		Arrays.fill(cnt, 0);
		cnt[0] = 1;
		
//		아래를 처음으로
		for(int i = 1; i <= L; i++) {
//			아래 문자부터 보기
			for(int j = 1; j <= orderL; j+=2) {
//				문자가 다르면 패스
				if(arr2[i] != check[j]) continue;
//				문자가 같으면 
				dp2[i][1][j] = cnt[j]+cnt[j-1];
			}
			
//			위에 문자 보기
			for(int j = 2; j <= orderL; j+=2) {
//				문자가 다르면 패스
				if(arr1[i] != check[j]) continue;
//				문자가 같으면 
				dp2[i][0][j] = cnt[j]+cnt[j-1];
			}
			
//			문자별 개수 값 갱신
			for(int j = 1; j <= orderL; j++) {
				cnt[j] = Math.max(cnt[j], Math.max(dp2[i][0][j], dp2[i][1][j]));
			}
		}
		
		answer += cnt[orderL];
		
		System.out.println(answer);
	}

}