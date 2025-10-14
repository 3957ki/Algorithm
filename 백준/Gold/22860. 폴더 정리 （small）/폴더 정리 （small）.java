import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.w3c.dom.Node;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int L = N + M;

		Map<String, Node> map = new HashMap<>();
		map.put("main", new Node());

		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			String P = st.nextToken();
			String F = st.nextToken();
			int type = Integer.parseInt(st.nextToken());

			if(!map.containsKey(P)) map.put(P, new Node());

			// 폴더
			if(type == 1){
				if(!map.containsKey(F)) map.put(F, new Node());
				map.get(P).folders.put(F, map.get(F));
			}
			// 파일
			else{
				map.get(P).files.add(F);
				map.get(P).cnt++;
			}
		}

		int Q = Integer.parseInt(br.readLine());
		while(Q-- > 0){
			String[] str = br.readLine().split("/");
			Node now = map.get(str[str.length-1]);

			Set<String> set = new HashSet<>();
			int cnt = DFS(now, set);
			sb.append(set.size()).append(" ").append(cnt).append("\n");
		}

		System.out.println(sb);
    }

	static int DFS(Node now, Set<String> set){

		set.addAll(now.files);
		int sum = now.cnt;
		for(Map.Entry<String, Node> entry : now.folders.entrySet()){
			sum += DFS(entry.getValue(), set);
		}
		return sum;
	}

	static class Node{
		int cnt;
		Set<String> files = new HashSet<>();
		Map<String, Node> folders = new HashMap<>();
	}
}