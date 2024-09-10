import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static int N, M, answer = Integer.MAX_VALUE;
	static String[] neck, chord, temp_chord;
	static HashMap<String, Integer> map = new HashMap<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st= new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map.put("A", 0);
		map.put("A#", 1);
		map.put("B", 2);
		map.put("C", 3);
		map.put("C#", 4);
		map.put("D", 5);
		map.put("D#", 6);
		map.put("E", 7);
		map.put("F", 8);
		map.put("F#", 9);
		map.put("G", 10);
		map.put("G#", 11);

		//        줄
		neck = new String[N];
		//        코드
		chord = new String[M];
		temp_chord = new String[N];

		st= new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			neck[i] = st.nextToken();
		}

		st= new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) {
			chord[i] = st.nextToken();
		}

		perm(0, 0);
		System.out.println(answer);
	}

	static void perm(int cnt, int visited) {
		if(cnt == N) {
			if(visited != (1<<M)-1) return;
			//난이도 구하기
			int min = Integer.MAX_VALUE;
			int max = 0;
			calc(0, min, max);
			return;
		}

		for(int i = 0; i < M; i++) {
			temp_chord[cnt] = chord[i];
			perm(cnt+1, visited|1<<i);
		}
	}

	static void calc(int cnt, int min, int max) {
		if(cnt == N) {
			if(max == 0 && min == Integer.MAX_VALUE) {
				System.out.println(0);
				System.exit(0);
			}
			answer = Math.min(max-min+1, answer);
			return;
		}

		//코드 음이 튜닝 음보다 클때
		if(map.get(temp_chord[cnt]) > map.get(neck[cnt])) {
			int fret = map.get(temp_chord[cnt]) - map.get(neck[cnt]);
			calc(cnt+1, Math.min(min, fret), Math.max(max, fret));
			calc(cnt+1, Math.min(min, fret+12), Math.max(max, fret+12));
		}
		//작을 때
		else if(map.get(temp_chord[cnt]) < map.get(neck[cnt])) {
			int fret = 12+map.get(temp_chord[cnt]) - map.get(neck[cnt]);
			calc(cnt+1, Math.min(min, fret), Math.max(max, fret));
			calc(cnt+1, Math.min(min, fret+12), Math.max(max, fret+12));
		}
		else {
			calc(cnt+1, min, max);
		}
	}

}