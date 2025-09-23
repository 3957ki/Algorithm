import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		TreeMap<Integer, Integer> map = new TreeMap<>();

		while(N-- > 0) {
			st = new StringTokenizer(br.readLine());
			int key = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			map.put(key, value);
		}

		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int key, value;
			Integer ceiling, floor;

			switch(cmd) {
				case 1:
					key = Integer.parseInt(st.nextToken());
					value = Integer.parseInt(st.nextToken());
					map.put(key, value);
					break;
				case 2:
					key = Integer.parseInt(st.nextToken());
					value = Integer.parseInt(st.nextToken());

					ceiling = map.ceilingKey(key);
					floor = map.floorKey(key);
					if(ceiling == null || ceiling - key > K) ceiling = null;
					if(floor == null || key - floor > K) floor = null;

					if(ceiling != null && floor != null) {
						if(ceiling.equals(floor)) map.put(key, value);
						if(ceiling - key > key - floor) map.put(floor, value);
						if(key - floor > ceiling - key) map.put(ceiling, value);
					}
					else if (ceiling != null) map.put(ceiling, value);
					else if (floor != null) map.put(floor, value);
					break;
				default:
					key = Integer.parseInt(st.nextToken());
					ceiling = map.ceilingKey(key);
					floor = map.floorKey(key);
					if(ceiling == null || ceiling - key > K) ceiling = null;
					if(floor == null || key - floor > K) floor = null;

					if(ceiling != null && floor != null) {
						if(ceiling.equals(floor)) sb.append(map.get(key)).append('\n');
						else if(ceiling - key > key - floor) sb.append(map.get(floor)).append('\n');
						else if(key - floor > ceiling - key) sb.append(map.get(ceiling)).append('\n');
						else sb.append('?').append('\n');
					}
					else if (ceiling != null) sb.append(map.get(ceiling)).append('\n');
					else if (floor != null) sb.append(map.get(floor)).append('\n');
					else sb.append(-1).append('\n');
			}
		}

		System.out.println(sb);
    }
}