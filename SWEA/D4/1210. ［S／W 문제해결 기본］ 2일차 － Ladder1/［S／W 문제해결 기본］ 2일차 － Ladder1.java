import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int m = 99;
	static int index = 0;
	static int[][] arr = new int[100][100];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int t = 1; t <= 10; t++) {
			int N = Integer.parseInt(br.readLine());
			m = 99;
			index = 0;
			arr = new int[100][100];
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 2) {
						index = j;
					}
				}
			}
			move();
			sb.append('#').append(N).append(' ').append(index).append('\n');
		}
		System.out.println(sb);
	}

	static void move() {
		if (index == 0) {
			while (m > 0) {
				m--;
				if (arr[m][index + 1] == 1) {
					turnR();
					return;
				}
			}
		} else if (index == 99) {
			while (m > 0) {
				m--;
				if (arr[m][index - 1] == 1) {
					turnL();
					return;
				}
			}
		} else {
			while (m > 0) {
				m--;
				if (arr[m][index - 1] == 1) {
					turnL();
					return;
				}
				if (arr[m][index + 1] == 1) {
					turnR();
					return;
				}
			}
		}
		return;
	}

	static void turnL() {
		while (index > 0) {
			index--;
			if(index != 0 && arr[m][index-1] == 0) {
				move();
				return;
			}
			if(index == 0) {
				move();
				return;
			}
		}
	}

	static void turnR() {
		while (index < 99) {
			index++;
			if(index != 99 && arr[m][index+1] == 0) {
				move();
				return;
			}
			if(index == 99) {
				move();
				return;
			}
		}
	}
}