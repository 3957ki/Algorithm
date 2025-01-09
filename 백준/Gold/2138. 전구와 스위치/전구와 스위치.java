import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String start = br.readLine();
        String end = br.readLine();

        int[] current = new int[N+1];
        int[] result = new int[N+1];

        for (int i = 0; i < N; i++) {
            // 다르면 1
            if(start.charAt(i) != end.charAt(i)) result[i] = 1;
        }

        // 1번을 안누를때
        int answer1 = 0;
        for (int i = 1; i < N; i++) {
            // 서로 다르면 누르기
            if(current[i-1] != result[i-1]) {
                answer1++;
                for (int j = -1; j <= 1; j++) {
                    current[i+j] ^= 1;
                }
            };
        }

        // 불가능
        if(current[N-1] != result[N-1]) answer1 = Integer.MAX_VALUE;

        current = new int[N+1];
        // 1번을 누를때
        int answer2 = 1;
        current[0] ^= 1;
        current[1] ^= 1;

        for (int i = 1; i < N; i++) {
            // 서로 다르면 누르기
            if(current[i-1] != result[i-1]) {
                answer2++;
                for (int j = -1; j <= 1; j++) {
                    current[i+j] ^= 1;
                }
            };
        }

        // 불가능
        if(current[N-1] != result[N-1]) answer2 = Integer.MAX_VALUE;

        int answer = Math.min(answer1, answer2);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);

    }
}