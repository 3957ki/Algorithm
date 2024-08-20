import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int cnt = n / 5;
		if (n % 5 == 0) {
			System.out.println(cnt);
		} else {
			while (cnt >= 0) {
				if ((n - 5 * cnt) % 3 == 0) {
					cnt += (n - 5 * cnt) / 3;
					System.out.println(cnt);
					break;
				} else if (cnt == 0) {
					System.out.println(-1);
					break;
				} else {
					cnt--;
				}
			}
		}

	}

}