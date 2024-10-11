import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] str = br.readLine().toCharArray();
		char[] pattern = br.readLine().toCharArray();
		
		int SL = str.length;
		int PL = pattern.length;
		
//		부분 일치 테이블 만들기
		int[] pi = new int[PL];
		pi[0] = 0;
		
		for(int i = 1, j = 0; i < PL; i++) {
//			접두사와 접미사가 일치하면
			if(pattern[i] == pattern[j]) {
				pi[i] = j+1;
				j++;
				continue;
			}
//			일치하지 않으면 j를 pi[j-1]위치로 옮기고 다시 같은 자리 비교
			else {
//				j가 0인데 불일치면 pi[i]는 0이고 다음 i보기 
				if(j == 0) {
					pi[i] = 0;
					continue;
				}
				j = pi[j-1];
				i--;
			}
		}
		
//		원본 문자열에서 탐색해보기
		
		int answer = 0;
		List<Integer> list = new ArrayList<>();
		
		for(int i = 0, j = 0; i < SL; i++) {
//			일치할 때
			if(str[i] == pattern[j]) {
//				끝까지 같다면 정답 갱신
				if(j == PL-1) {
					answer++;
					list.add(i-j+1);
					j = pi[j];
					continue;
				}
				j++;
			}
//			불일치라면
			else {
				if(j == 0) continue;
				j = pi[j-1];
				i--;
			}
			
		}
		
		sb.append(answer).append('\n');
		for(int num : list) {
			sb.append(num).append(' ');
		}
		System.out.println(sb);
	}

}