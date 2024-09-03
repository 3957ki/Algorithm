import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	static int[] parents;
	static int G;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		G = Integer.parseInt(br.readLine());
		int P = Integer.parseInt(br.readLine());
		
		parents = new int[G+1];
		make();
		
//		num자리에 해당하는 부모가 다음에 들어갈 자리를 의미
		int answer = 0;
		for(int i = 0; i < P; i++) {
			int num = findSet(Integer.parseInt(br.readLine()));
			
//			들어갈 자리가 없어서 부모가 0이면 break
			if(num == 0) break;
//			다음 자리와 union, 이제 num을 부모로 하는 자리들은 num-1을 부모로 가리킴
			union(num-1, num);
			answer++;
		}
		
		System.out.println(answer);
		
	}
	
	static void make() {
		for(int i = 1; i <=G; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

}