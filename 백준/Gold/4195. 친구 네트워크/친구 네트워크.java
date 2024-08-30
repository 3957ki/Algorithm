import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static List<Integer> parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			HashMap<String, Integer> map = new HashMap<>();
			int cnt = 0;
			parents = new ArrayList<>();
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String f1 = st.nextToken();
				String f2 = st.nextToken();
//				f1이 처음보는 친구라면
				if(!map.containsKey(f1)) {
//					map에 cnt를 값으로 해서 넣기
					map.put(f1, cnt++);
					parents.add(-1);
				}
//				f2도 마찬가지
				if(!map.containsKey(f2)) {
					map.put(f2, cnt++);
					parents.add(-1);
				}
//				union하기
				union(map.get(f1), map.get(f2));
//				parents의 f1 부모 인덱스에 접근해서 트리안의 노드의 수를 얻어오기
				sb.append(Math.abs(parents.get(findSet(map.get(f1))))).append('\n');
			}
		}
		System.out.println(sb);
	}

	static int findSet(int a) {
//		음수라면 루트임
		if(parents.get(a) < 0) return a;
//		아니라면 루트값을 찾아서 갱신
		parents.set(a, findSet(parents.get(a)));
		return parents.get(a);
	}
	
	static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
//		루트가 같으면 false
		if(aRoot == bRoot) return false;
//		parents의 aRoot인덱스 값을 노드개수의 합으로 변경
		parents.set(aRoot, parents.get(aRoot) + parents.get(bRoot));
//		bRoot의 부모를 aRoot로 변경
		parents.set(bRoot, aRoot);
		return true;
	}
}