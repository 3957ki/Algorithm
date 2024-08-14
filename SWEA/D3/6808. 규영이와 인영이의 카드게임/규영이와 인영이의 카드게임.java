import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static boolean[] visited;
	static int[] gyulist;
	static List<Integer> inlist;
	static int win, lose;
	static int gyusum = 0;
	static int insum = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			visited = new boolean[19];
			gyulist = new int[9];
			inlist = new ArrayList<>(9);
			win = 0;
			lose = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			boolean[] gyu = new boolean[19];
			for(int i = 0; i < 9; i++) {
				int num = Integer.parseInt(st.nextToken());
				gyulist[i] = num;
				gyu[num] = true;
			}
			for(int i = 1; i <= 18; i++) {
				if(!gyu[i]) {
					inlist.add(i);
				}
			}
			perm(0);
			sb.append('#').append(t).append(' ').append(win).append(' ').append(lose).append('\n');
		}
		System.out.println(sb);
	}
	
	static void perm(int cnt) {
		if(cnt == 9) {
			if(gyusum > insum) {
				win++;
			}
			else if(gyusum < insum) {
				lose++;
			}
			return;
		}
		
		for(int i : inlist) {
			if(visited[i]) continue;
			visited[i] = true;
			
			if(gyulist[cnt] > i) {
				gyusum+=gyulist[cnt]+i;
			}
			else {
				insum+=gyulist[cnt]+i;
			}
			
			perm(cnt+1);
			
			if(gyulist[cnt] > i) {
				gyusum-=gyulist[cnt]+i;
			}
			else {
				insum-=gyulist[cnt]+i;
			}
			
			visited[i] = false;
		}
	}
}