import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		
		Room[] rooms = new Room[N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int type = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			rooms[i] = new Room(type, a, h);
		}
		
		long low = 1;
		long high = Long.MAX_VALUE/2;
		long mid;
		long answer = high;
		A: while(low <= high) {
//			현재 max 체력
			mid = (low+high)/2;
			long curHP = mid;
			long curA = A;
//			시뮬레이션
			
			for(Room room : rooms) {
				long a = room.a;
				long h = room.h;
				
//				전투
				if(room.type ==1) {
					long attackCnt = h%curA == 0 ? h/curA : h/curA+1;
					long hitCnt = curHP%a == 0 ? curHP/a : curHP/a+1;
					
//				이길 경우
					if(attackCnt <= hitCnt) {
						curHP -= (attackCnt-1)*a;
						continue;
					}
//				질 경우
					else {
						low = mid+1;
						continue A;
					}
				}
				
//				회복
				else {
					curA+=a;
					curHP = Math.min(curHP+h, mid);
				}
				
			}
			answer = Math.min(answer, mid);
			high = mid-1;
		}
		System.out.println(answer);
	}
	
	static class Room{
		int type, a, h;

		public Room(int type, int a, int h) {
			this.type = type;
			this.a = a;
			this.h = h;
		}
		
	}

}